package com.tdl.plugins

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.contentType
import io.ktor.http.ContentType
import io.ktor.serialization.gson.gson


object ConfigureClient {
    val client = HttpClient(CIO) {
        defaultRequest {
            contentType(ContentType.Application.Json)
        }
        install(ContentNegotiation) {
            gson {
                serializeNulls()
            }
        }
        install(HttpRequestRetry) {
            retryOnExceptionOrServerErrors(3)
            exponentialDelay()
        }
    }

}
