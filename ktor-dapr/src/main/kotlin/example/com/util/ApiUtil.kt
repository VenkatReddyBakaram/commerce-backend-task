package example.com.util

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.path
import io.ktor.server.response.respond
import java.util.Date

data class APIResponse(
    var status: HttpStatusCode = HttpStatusCode.OK,
    var data: Any? = null,
    var errorMessage: String? = null
)

private data class APIPayload(val path: String, val timestamp: Date, val data: Any?, val errorMessage: String? = null)

internal suspend fun ApplicationCall.respond(response: APIResponse) {
    if (response.status.value < HttpStatusCode.BadRequest.value) {
        response.errorMessage = null
    }
    respond(response.status, APIPayload(request.path(), Date(), response.data, response.errorMessage))
}
