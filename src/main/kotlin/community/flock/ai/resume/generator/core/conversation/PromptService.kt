package community.flock.ai.resume.generator.core.conversation

import com.aallam.openai.api.chat.ChatCompletionRequest
import com.aallam.openai.api.chat.ChatMessage
import com.aallam.openai.api.chat.ChatRole.Companion.Assistant
import com.aallam.openai.api.chat.ChatRole.Companion.Function
import com.aallam.openai.api.chat.ChatRole.Companion.System
import com.aallam.openai.api.chat.ChatRole.Companion.User
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.client.OpenAI
import community.flock.ai.resume.generator.core.function.createJobDescriptionItem
import community.flock.ai.resume.generator.core.resume.WorkExperienceItem
import community.flock.ai.resume.generator.core.resume.WorkExperienceItemService
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.springframework.stereotype.Component

@Component
class PromptService(
    private val openAI: OpenAI,
    private val workExperienceItemService: WorkExperienceItemService,
) {
    private val messages: MutableList<ChatMessage> = mutableListOf(
        ChatMessage(
            System,
            content = """
                You are helping write a resume for a software developer, specifically a list of previous job descriptions. Each item should include at least the following things:

                - The name of the employer
                - The period of time spent working for the employer
                - The technologies used
                - A short summary of the job

                Ask the user for an initial description and keep asking questions until you are sure you have all the information you need. Once you have all the information, create the job description item.

                An example of an item is this:

                (start of example)

                DHL (May 2022 - Present)
                Java, Spring Boot, React

                Worked as a fullstack engineer on a project with 50.000 concurrent users. Focused on scalability and low latency processing.

                (end of example)
            """.trimIndent(),
        ),
    )

    suspend fun processUserInput(message: String): Message {
        val chatMessage = ChatMessage(role = User, content = message)

        messages.add(chatMessage)

        val request = ChatCompletionRequest(
            model = ModelId("gpt-3.5-turbo"),
            messages = messages,
            functions = listOf(createJobDescriptionItem),
        )

        val response = openAI
            .chatCompletion(request)
            .choices
            .first()
            .message

        println("message = $response")

        messages.add(response)

        val processedResponse = response.processResponse()

        return response.toDomain(processedResponse)
    }

    fun ChatMessage.toDomain(message: String) = Message(
        type = when (role) {
            User -> MessageType.USER
            System, Assistant, Function -> MessageType.AGENT
            else -> error("Unknown role")
        },
        contents = message,
    )

    fun ChatMessage.processResponse(): String = when {
        content != null -> content!!
        functionCall != null -> processFunctionCall()
        else -> error("Message contains neither content nor a function call")
    }

    fun ChatMessage.processFunctionCall(): String {
        functionCall?.let {
            val args = it.argumentsAsJson()

            return when (it.name) {
                createJobDescriptionItem.name ->
                    workExperienceItemService.create(
                        args.getStringValue("employerName"),
                        args.getStringValue("period"),
                        args.getStringValue("technologiesUsed"),
                        args.getStringValue("summary"),
                    ).toResponseString()
                else -> error("Unknown function name")
            }
        } ?: error("Attempted to process function call, but functionCall is null")
    }

    fun JsonObject.getStringValue(key: String) = this[key]?.jsonPrimitive?.content ?: ""
}

// TODO: This doesn't belong here
fun WorkExperienceItem.toResponseString() = """
    I created a work experience item!
    
    Employer: $employerName
    Period: $period
    Technologies used: $technologiesUsed
    Summary: $summary
""".trimIndent()
