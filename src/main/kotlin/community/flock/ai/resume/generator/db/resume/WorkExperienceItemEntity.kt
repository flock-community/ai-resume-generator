package community.flock.ai.resume.generator.db.resume

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.*

@Entity
class WorkExperienceItemEntity(
    @Id
    val id: String = UUID.randomUUID().toString(),
    val employerName: String?,
    val period: String?,
    val technologiesUsed: String?,
    val summary: String?,
)
