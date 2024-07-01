package com.tdl.config

import com.tdl.plugins.ConfigureClient
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import org.litote.kmongo.coroutine.CoroutineDatabase

object MongoConfigFactory {
    private val dbConnect = dbConnect()
    private val mongoClient = MongoConfig(dbConnect.databaseName, dbConnect.connectionString)
    fun getDatabase(): CoroutineDatabase {
        return mongoClient.getDatabase()
    }
}

fun dbConnect(): MongoDBPropDTO {
    var connectionString = ""
    var databaseName = ""
    var dbResponse: String
    try {
        runBlocking {
            dbResponse =
                ConfigureClient.client.get("http://localhost:50006/v1.0/state/statestore/mongoDBConfig").bodyAsText()
        }
        val data = Json.decodeFromString<Map<String, String>>(dbResponse)
        connectionString = data.get("connectionString")!!
        databaseName = data.get("dbName")!!
    } catch (_: Exception) {
    }
    println("Connection String === [$connectionString] Database Name === [$databaseName]")
    return MongoDBPropDTO(
        connectionString,
        databaseName
    )
}

data class MongoDBPropDTO(
    val connectionString: String,
    val databaseName: String
)
