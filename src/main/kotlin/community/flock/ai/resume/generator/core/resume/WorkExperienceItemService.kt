package community.flock.ai.resume.generator.core.resume

import org.springframework.stereotype.Service

@Service
class WorkExperienceItemService(
    private val workExperienceItemRepositoryAdapter: WorkExperienceItemRepositoryAdapter,
) {

    fun create(
        employerName: String,
        period: String,
        technologiesUsed: String,
        summary: String,
    ) {
        WorkExperienceItem(
            employerName,
            period,
            technologiesUsed,
            summary,
        ).let {
            workExperienceItemRepositoryAdapter.save(it)
        }
    }

    fun findAll() = workExperienceItemRepositoryAdapter.findAll()
}
