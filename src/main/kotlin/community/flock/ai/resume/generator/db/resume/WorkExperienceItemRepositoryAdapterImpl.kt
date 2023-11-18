package community.flock.ai.resume.generator.db.resume

import community.flock.ai.resume.generator.core.resume.WorkExperienceItem
import community.flock.ai.resume.generator.core.resume.WorkExperienceItemRepositoryAdapter
import org.springframework.stereotype.Service

@Service
class WorkExperienceItemRepositoryAdapterImpl(
    private val workExperienceItemRepository: WorkExperienceItemRepository,
) : WorkExperienceItemRepositoryAdapter {

    override fun save(item: WorkExperienceItem) =
        item
            .mapToEntity()
            .let {
                workExperienceItemRepository.save(it)
            }
}

private fun WorkExperienceItem.mapToEntity() = WorkExperienceItemEntity(
    employerName = employerName,
    period = period,
    technologiesUsed = technologiesUsed,
    summary = summary,
)
