package community.flock.ai.resume.generator.core

import com.aallam.openai.api.chat.ChatCompletionRequest
import com.aallam.openai.api.chat.ChatMessage
import com.aallam.openai.api.chat.ChatRole
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.client.OpenAI
import community.flock.ai.resume.generator.core.function.createJobDescriptionItem
import community.flock.ai.resume.generator.core.model.Message
import community.flock.ai.resume.generator.core.model.MessageType
import org.springframework.stereotype.Component

@Component
class PromptService(
    private val openAI: OpenAI,
) {
    private val messages: MutableList<ChatMessage> = mutableListOf(
        ChatMessage(
            ChatRole.System,
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
        val chatMessage = ChatMessage(role = ChatRole.User, content = message)

        messages.add(chatMessage)

        val request = ChatCompletionRequest(
            model = ModelId("gpt-3.5-turbo"),
            messages = messages,
            functions = listOf(createJobDescriptionItem),
        )

        val response = openAI
            .chatCompletion(request)
            .choices
            .first
            .message
        println("message = $response")
        messages.add(response)

        return response.toDomain()
    }

    fun ChatMessage.toDomain() = Message(
        type = when (role) {
            ChatRole.System -> MessageType.AGENT
            ChatRole.User -> MessageType.USER
            ChatRole.Assistant -> MessageType.AGENT
            ChatRole.Function -> MessageType.AGENT
            else -> error("Unknown role")
        },
        contents = this.content ?: "Function called",
    )
}
