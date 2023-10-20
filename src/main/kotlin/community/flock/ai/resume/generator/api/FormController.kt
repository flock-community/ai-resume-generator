package community.flock.ai.resume.generator.api

import community.flock.ai.resume.generator.core.PromptService
import community.flock.ai.resume.generator.core.model.GenerateResumeExperienceItemRequest
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping

@Controller
class FormController(val promptService: PromptService) {

    @GetMapping("/prompt")
    fun getPrompt(model: Model): String {
        model.addAttribute("prompt", Prompt(""))
        return "prompt"
    }

    @PostMapping("/prompt")
    fun postPrompt(@ModelAttribute prompt: Prompt, model: Model): String {
        val result = Prompt(generateFixedItem().text)
        model.addAttribute("prompt", result)
        return "result"
    }

    private fun generateFixedItem() = promptService.generateResumeExperienceItem(
        GenerateResumeExperienceItemRequest(
            companyName = "DHL",
            period = "Last year",
            technologiesUsed = "TypeScript, React",
            otherInformation = "Built a frontend",
        ),
    )
}

data class Prompt(
    val content: String,
)
