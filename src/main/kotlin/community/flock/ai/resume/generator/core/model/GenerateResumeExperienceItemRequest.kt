package community.flock.ai.resume.generator.core.model

data class GenerateResumeExperienceItemRequest(
    val companyName: String,
    val period: String,
    val technologiesUsed: String,
    val otherInformation: String,
)
