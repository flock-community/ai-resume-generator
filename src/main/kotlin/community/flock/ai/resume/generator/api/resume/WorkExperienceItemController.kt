package community.flock.ai.resume.generator.api.resume

import community.flock.ai.resume.generator.core.resume.WorkExperienceItemService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/work-experience-item")
data class WorkExperienceItemController(
    private val service: WorkExperienceItemService
) {

    @GetMapping
    fun findAll(): List<WorkExperienceItemResponse> = service.findAll().map { it.toResponse() }
}