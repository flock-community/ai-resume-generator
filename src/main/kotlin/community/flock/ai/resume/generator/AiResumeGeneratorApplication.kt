package community.flock.ai.resume.generator

import community.flock.ai.resume.generator.core.config.OpenAiProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(OpenAiProperties::class)
class AiResumeGeneratorApplication

fun main(args: Array<String>) {
    runApplication<AiResumeGeneratorApplication>(*args)
}
