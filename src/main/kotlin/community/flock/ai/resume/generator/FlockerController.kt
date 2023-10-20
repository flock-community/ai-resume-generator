package community.flock.ai.resume.generator

import community.flock.wirespec.generated.Flocker
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class FlockerController(val flockerService: FlockerService){

    @PutMapping("/flocker")
    fun store(@RequestBody flocker: Flocker): Flocker = flockerService.store(flocker)
}

@Service
class FlockerService(val flockerRepository: FlockerRepository) {
    fun store(flocker: Flocker): Flocker {
        flockerRepository.save(flocker.toDbModel())
        return flocker
    }
}

@Repository
interface FlockerRepository: PagingAndSortingRepository<DbFlocker, Long> {
    fun save(flocker: DbFlocker)
}

