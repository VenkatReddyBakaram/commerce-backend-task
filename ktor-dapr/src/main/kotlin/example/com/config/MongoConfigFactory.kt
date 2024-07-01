package example.com.config

import org.litote.kmongo.coroutine.CoroutineDatabase

object MongoConfigFactory {
    val dbUrl = "mongodb://localhost:27017/"
    val dbName = "TestDBB"
    private val mongoClient = MongoConfig(dbName, dbUrl)
    fun getDatabase(): CoroutineDatabase {
        return mongoClient.getDatabase()
    }
}
