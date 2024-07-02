package com.tdl.config

import com.tdl.plugins.ConfigureClient
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import org.litote.kmongo.coroutine.CoroutineDatabase

object MongoConfigFactory {
    private val mongoClient =
        MongoConfig(MongoDBProps.mongoDBProps.databaseName, MongoDBProps.mongoDBProps.connectionString)

    fun getDatabase(): CoroutineDatabase {
        return mongoClient.getDatabase()
    }
}

fun dbConnect(): MongoDBPropDTO {
    var dbResponse: String
    runBlocking {
        dbResponse =
            ConfigureClient.client.get("http://localhost:50006/v1.0/state/statestore/mongoDBConfig").bodyAsText()
    }
    val data = Json.decodeFromString<Map<String, String>>(dbResponse)
    val connectionString = data["connectionString"]!!
    val databaseName = data["dbName"]!!
    val hostName = data["hostName"]!!
    val portNumber = data["portNumber"]!!

    println("Connection String === [$connectionString] Database Name === [$databaseName]")
    return MongoDBPropDTO(
        connectionString,
        databaseName,
        hostName,
        portNumber
    )
}

data class MongoDBPropDTO(
    var connectionString: String = "",
    var databaseName: String = "",
    var hostName: String = "",
    var portNumber: String = ""
)
