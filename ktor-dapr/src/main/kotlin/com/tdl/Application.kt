package com.tdl

import com.tdl.config.MongoConfigFactory
import com.tdl.config.MongoDBProps.mongoDBProps
import com.tdl.config.dbConnect
import com.tdl.plugins.*
import io.ktor.server.application.*

fun main(args: Array<String>): Unit =
    io.ktor.server.cio.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    mongoDBProps = dbConnect()
    configureDI()
    configureSerialization()
    configureRouting()
    configureStatusPages()
    //ConfigureJedisClient.redisClient.flushAll() // To flush the data from redis
    MongoConfigFactory.getDatabase()
}
