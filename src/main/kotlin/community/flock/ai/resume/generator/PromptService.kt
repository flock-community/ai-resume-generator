package community.flock.ai.resume.generator

import org.springframework.ai.client.AiClient
import org.springframework.ai.client.Generation
import org.springframework.ai.prompt.Prompt
import org.springframework.ai.prompt.SystemPromptTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.stereotype.Component

@Component
class PromptService(
    private val aiClient: AiClient,
) {

    @Value("classpath:/prompts/resume-experience-item.st")
    private lateinit var systemResource: Resource

    fun generateResumeExperienceItem(): Generation {
        val systemPromptTemplate = SystemPromptTemplate(systemResource)
        val systemMessage = systemPromptTemplate.createMessage(
            mapOf(
                "company_name" to "DHL",
                "period" to "Started april this year, still working there",
                "technologies_used" to "Java, Spring, Netty, TCP, Kafka, AVRO",
                "other_information" to "Interface with devices over TCP sockets, create adapter to have uniform API for different devices",
            ),
        )
        val prompt = Prompt(systemMessage)

        val response = aiClient.generate(prompt).generation

        return response
    }
}
