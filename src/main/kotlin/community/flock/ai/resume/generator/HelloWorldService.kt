package community.flock.ai.resume.generator

import jakarta.annotation.PostConstruct
import org.springframework.ai.client.AiClient
import org.springframework.stereotype.Component

@Component
class HelloWorldService(
    private val aiClient: AiClient,
) {

    fun hello() = aiClient.generate("Hello, OpenAI!")

    @PostConstruct
    fun postConstruct() = println("PC: ${hello()}")
}
