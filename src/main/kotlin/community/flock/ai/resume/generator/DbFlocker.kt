package community.flock.ai.resume.generator

import community.flock.wirespec.generated.Flocker
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.UUID

@Entity
data class DbFlocker(
    @Id
    val id: String = UUID.randomUUID().toString(),
    val firstName: String?,
    val lastName: String?,
    val dateOfBirth: String?,
    val phoneNumber: Int?,
) {
    constructor() : this(
        firstName = null,
        lastName = null,
        dateOfBirth = null,
        phoneNumber = null,
    )
}

fun Flocker.toDbModel() =
    DbFlocker(
        id = "1",
        firstName = firstName,
        lastName = lastName,
        dateOfBirth = dateOfBirth,
        phoneNumber = phoneNumber,
    )
