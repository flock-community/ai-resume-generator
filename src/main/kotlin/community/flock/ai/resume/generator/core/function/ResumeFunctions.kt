package community.flock.ai.resume.generator.core.function

import com.aallam.openai.api.chat.ChatCompletionFunction
import com.aallam.openai.api.chat.Parameters
import kotlinx.serialization.json.put
import kotlinx.serialization.json.putJsonObject

val createJobDescriptionItem = ChatCompletionFunction(
    name = "createJobDescriptionItem",
    description = "Create a job description item",
    parameters = Parameters.buildJsonObject {
        put("type", "object")
        putJsonObject("properties") {
            putJsonObject("employerName") {
                put("type", "string")
                put("description", "The name of the employer")
            }
            putJsonObject("period") {
                put("type", "string")
                put("description", "The period of time spent working for the employer")
            }
            putJsonObject("technologiesUsed") {
                put("type", "string")
                put("technologiesUsed", "The technologies used")
            }
            putJsonObject("summary") {
                put("type", "string")
                put("summary", "A short summary of the job")
            }
        }
    },
)
