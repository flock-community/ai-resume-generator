package community.flock.ai.resume.generator.core.conversation

data class Message(
    val type: MessageType,
    val contents: String,
)

enum class MessageType {
    USER, AGENT, NOTIFICATION
}
