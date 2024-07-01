package com.tdl.config


import com.tdl.util.getKoinInstance
import io.dapr.client.DaprClient
import io.dapr.exceptions.DaprException
import mu.KotlinLogging

val log = KotlinLogging.logger {}

fun getMongoDbSecrets(): MongoDBPropDTO {

    var connectionString = ""
    var databaseName = ""
    try {
        connectionString = getKoinInstance<DaprClient>()
            .getSecret("mongoDBVault", "connectionString")
            .block()?.get("connectionString")?.toString() ?: ""

        databaseName = getKoinInstance<DaprClient>()
            .getSecret("mongoDBVault", "databaseName")
            .block()?.get("databaseName")?.toString() ?: ""
    } catch (vaultError: DaprException) {
        log.error("Key vault error while fetching key: ${vaultError}")
    }
    println("DB Connection String :$connectionString")
    println("DB Name :$databaseName")
    //connectionString = "mongodb://localhost:27017/"
    //databaseName = "TestDB"
    return MongoDBPropDTO(connectionString, databaseName)
}