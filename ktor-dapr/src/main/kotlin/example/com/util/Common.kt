package example.com.util

import io.ktor.server.application.ApplicationCall

suspend fun ApplicationCall.respond(init: APIResponse.() -> Unit) {
    val response = APIResponse()
    response.init()
    respond(response)
}