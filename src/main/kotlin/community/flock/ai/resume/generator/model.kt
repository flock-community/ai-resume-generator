package community.flock.ai.resume.generator

import community.flock.wirespec.generated.Flocker
import jakarta.persistence.Entity

@Entity
class DbFlocker (
    val firstName: String,
    val lastName: String,
    val dateOfBirth: String,
    val phoneNumber: Int
)

fun Flocker.toDbModel() = DbFlocker(firstName, lastName, dateOfBirth, phoneNumber)