package community.flock.ai.resume.generator.flocker

import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface FlockerRepository : PagingAndSortingRepository<DbFlocker, Long> {
    fun save(flocker: DbFlocker)
}
