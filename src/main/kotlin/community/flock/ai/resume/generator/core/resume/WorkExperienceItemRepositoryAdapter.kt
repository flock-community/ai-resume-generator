package community.flock.ai.resume.generator.core.resume

import community.flock.ai.resume.generator.db.resume.WorkExperienceItemEntity

interface WorkExperienceItemRepositoryAdapter {

    fun save(item: WorkExperienceItem): WorkExperienceItemEntity
}
