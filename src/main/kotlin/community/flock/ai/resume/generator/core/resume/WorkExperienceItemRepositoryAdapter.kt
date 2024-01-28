package community.flock.ai.resume.generator.core.resume

interface WorkExperienceItemRepositoryAdapter {

    fun save(item: WorkExperienceItem): WorkExperienceItem

    fun findAll(): List<WorkExperienceItem>
}
