package example.com.config

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.connection.ConnectionPoolSettings
import org.litote.kmongo.coroutine.CoroutineClient
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class MongoConfig(databaseName: String, databaseUrl: String) {
    private val log: Logger = LoggerFactory.getLogger(javaClass)

    private var client: CoroutineClient
    private var database: CoroutineDatabase
    val IDLETIME: Long = 24000
    val MINSIZE = 5
    val MAXSIZE = 40

    init {
        log.info("Loading Mongo Config")
        client = KMongo.createClient(
            MongoClientSettings.builder()
                .applyConnectionString(ConnectionString(databaseUrl))
                .applyToConnectionPoolSettings {
                    ConnectionPoolSettings.builder()
                        .minSize(MINSIZE).maxSize(MAXSIZE)
                }
                .applicationName("omni-store")
                .build()).coroutine
        database = client.getDatabase(databaseName)
    }

    fun getDatabase(): CoroutineDatabase {
        return database
    }
}