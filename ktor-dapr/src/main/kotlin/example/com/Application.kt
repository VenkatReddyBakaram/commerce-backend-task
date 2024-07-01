package example.com

import example.com.config.MongoConfigFactory
import example.com.plugins.configureSerialization
import example.com.plugins.configureRouting
import example.com.plugins.configureStatusPages
import example.com.plugins.configureDI
import io.ktor.server.application.Application

fun main(args: Array<String>): Unit =
    io.ktor.server.cio.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    configureSerialization()
    configureRouting()
    configureStatusPages()
    configureDI()
    MongoConfigFactory.getDatabase()
}
