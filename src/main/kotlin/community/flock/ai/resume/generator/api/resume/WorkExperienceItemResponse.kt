package community.flock.ai.resume.generator.api.resume

import community.flock.ai.resume.generator.core.resume.WorkExperienceItem

data class WorkExperienceItemResponse(
    val employerName: String,
    val period: String,
    val summary: String,
    val technologiesUsed: String
)

fun WorkExperienceItem.toResponse(): WorkExperienceItemResponse =
    WorkExperienceItemResponse(
        this.employerName,
        this.period,
        this.summary,
        this.technologiesUsed
    )