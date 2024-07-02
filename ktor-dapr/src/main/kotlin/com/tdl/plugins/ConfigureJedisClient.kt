package com.tdl.plugins

import com.tdl.config.MongoDBProps
import redis.clients.jedis.Jedis

object ConfigureJedisClient {
    val redisClient = Jedis(MongoDBProps.mongoDBProps.hostName, MongoDBProps.mongoDBProps.portNumber.toInt())
}
