package com.tdl

import com.tdl.config.MongoConfigFactory
import com.tdl.plugins.configureDI
import com.tdl.plugins.configureRouting
import com.tdl.plugins.configureSerialization
import com.tdl.plugins.configureStatusPages
import io.ktor.server.application.*

fun main(args: Array<String>): Unit =
    io.ktor.server.cio.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    configureDI()
    configureSerialization()
    configureRouting()
    configureStatusPages()
    MongoConfigFactory.getDatabase()
}
