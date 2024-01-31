package community.flock.ai.resume.generator.db.resume

import community.flock.ai.resume.generator.core.resume.WorkExperienceItem
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.*

@Entity
class WorkExperienceItemEntity(
    @Id
    val id: String = UUID.randomUUID().toString(),
    val employerName: String,
    val period: String,
    val technologiesUsed: String,
    val summary: String,
) {
    fun toDomain() = WorkExperienceItem(
        employerName,
        period,
        technologiesUsed,
        summary
    )
}

fun WorkExperienceItem.toEntity() = WorkExperienceItemEntity(
    employerName = employerName,
    period = period,
    technologiesUsed = technologiesUsed,
    summary = summary,
)
