package community.flock.ai.resume.generator.api.websockets

import org.slf4j.LoggerFactory
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller

@Controller
class WebsocketController {

    @MessageMapping("/chat")
    @SendTo("/topic/chat")
    fun hello(message: String): Message {
        logger.info("Received message, message = {}", message)

        return Message("You sent: $message")
    }

    companion object {
        private val logger = LoggerFactory.getLogger(WebsocketController::class.java)
    }
}
