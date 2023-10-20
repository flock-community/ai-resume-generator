package community.flock.ai.resume.generator.core

import community.flock.ai.resume.generator.core.model.GenerateResumeExperienceItemRequest
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

    fun generateResumeExperienceItem(request: GenerateResumeExperienceItemRequest): Generation {
        val systemPromptTemplate = SystemPromptTemplate(systemResource)
        val systemMessage = systemPromptTemplate.createMessage(
            with(request) {
                mapOf(
                    "company_name" to companyName,
                    "period" to period,
                    "technologies_used" to technologiesUsed,
                    "other_information" to otherInformation,
                )
            },
        )
        val prompt = Prompt(systemMessage)

        val response = aiClient.generate(prompt)

        return response.generation
    }

    fun sendSimpleMessage(message: String) =
        Prompt(message)
            .let { aiClient.generate(it) }
            .generation
            .text
}
