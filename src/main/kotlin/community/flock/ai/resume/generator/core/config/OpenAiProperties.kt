package community.flock.ai.resume.generator.core.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "open-ai")
data class OpenAiProperties(
    val apiKey: String,
)
