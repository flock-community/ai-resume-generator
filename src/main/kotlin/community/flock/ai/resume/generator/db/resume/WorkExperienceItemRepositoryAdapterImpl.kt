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
            .toEntity()
            .let {
                workExperienceItemRepository.save(it)
            }
            .toDomain()

    override fun findAll(): List<WorkExperienceItem> =
        workExperienceItemRepository.findAll().map { it.toDomain() }
}