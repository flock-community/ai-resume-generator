package community.flock.ai.resume.generator.core

import com.aallam.openai.api.chat.ChatCompletionRequest
import com.aallam.openai.api.chat.ChatMessage
import com.aallam.openai.api.chat.ChatRole
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.client.OpenAI
import org.springframework.stereotype.Component

@Component
class PromptService(
    private val openAI: OpenAI,
) {

    suspend fun sendSimpleMessage(message: String): String {
        val request = ChatCompletionRequest(
            model = ModelId("gpt-3.5-turbo"),
            messages = listOf(
                ChatMessage(role = ChatRole.User, content = message),
            ),
        )
        return openAI
            .chatCompletion(request)
            .choices
            .map { it.message.content }
            .joinToString()
    }
}
