package community.flock.ai.resume.generator.core.config

import com.aallam.openai.client.OpenAI
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenAiConfig {
    @Bean
    fun openAI(openAiProperties: OpenAiProperties) = OpenAI(openAiProperties.apiKey)
}
