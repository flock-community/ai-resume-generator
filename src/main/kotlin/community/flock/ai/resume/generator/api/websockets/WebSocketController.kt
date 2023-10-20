package community.flock.ai.resume.generator.api.websockets

import community.flock.ai.resume.generator.core.PromptService
import org.slf4j.LoggerFactory
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller

@Controller
class WebSocketController(
    private val promptService: PromptService,
) {

    @MessageMapping("/chat")
    @SendTo("/topic/chat")
    fun hello(message: String): Message {
        logger.info("Received message, message = {}", message)

        val answer = promptService.sendSimpleMessage(message)

        return Message(
            """
            You asked: "$message". ChatGPT answered: "$answer"
            """.trimIndent(),
        )
    }

    companion object {
        private val logger = LoggerFactory.getLogger(WebSocketController::class.java)
    }
}
