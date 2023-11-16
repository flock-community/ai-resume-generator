package community.flock.ai.resume.generator.Flocker

import community.flock.wirespec.generated.Flocker
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class FlockerController(val flockerService: FlockerService) {

    @PutMapping("/flocker")
    fun store(@RequestBody flocker: Flocker): Flocker = flockerService.store(flocker)
}