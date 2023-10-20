package community.flock.ai.resume.generator

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class AiResumeGeneratorApplication

fun main(args: Array<String>) {
    runApplication<AiResumeGeneratorApplication>(*args)
}
