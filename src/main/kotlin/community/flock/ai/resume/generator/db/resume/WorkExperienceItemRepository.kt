package community.flock.ai.resume.generator.db.resume

import org.springframework.data.repository.ListCrudRepository

interface WorkExperienceItemRepository : ListCrudRepository<WorkExperienceItemEntity, String>
