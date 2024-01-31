package community.flock.ai.resume.generator.flocker

import community.flock.wirespec.generated.Flocker
import org.springframework.stereotype.Service

@Service
class FlockerService(val flockerRepository: FlockerRepository) {
    fun store(flocker: Flocker): Flocker {
        println(flocker.toDbModel())
        flockerRepository.save(flocker.toDbModel())
        return flocker
    }
}
