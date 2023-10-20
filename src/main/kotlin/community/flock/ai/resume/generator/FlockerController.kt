package community.flock.ai.resume.generator

import community.flock.wirespec.generated.Flocker
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Controller
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PutMapping

@Controller
class FlockerController(val flockerService: FlockerService){

    @PutMapping("/flocker")
    fun store(flocker: Flocker): Flocker = flockerService.store(flocker)
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

