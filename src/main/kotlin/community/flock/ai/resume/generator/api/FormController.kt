package community.flock.ai.resume.generator.api

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping

@Controller
class FormController() {

    @GetMapping("/prompt")
    fun getPrompt(model: Model): String {
        model.addAttribute("prompt", Prompt(""))
        return "prompt"
    }

    @PostMapping("/prompt")
    fun postPrompt(@ModelAttribute prompt: Prompt, model: Model): String {
        val result = Prompt("Some text")
        model.addAttribute("prompt", result)
        return "result"
    }
}

data class Prompt(
    val content: String,
)
