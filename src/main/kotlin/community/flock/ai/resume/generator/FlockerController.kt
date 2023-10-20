import org.springframework.stereotype.Controller
import org.springframework.stereotype.Repository
import java.util.Date

@Controller
FlockerController(val flockerService: FlockerService)

class FlockerService(val flockerRepository: FlockerRepository) {
    fun store(flocker: FlockerData){
        flockerRepository.store()
    }

    data class FlockerData (
        val firstName: String,
        val lastName: String,
        val dateOfBirth: Date
    )

}

@Repository
class FlockerRepository: PagingAndSortingRepository<Crag, Long>{

}

