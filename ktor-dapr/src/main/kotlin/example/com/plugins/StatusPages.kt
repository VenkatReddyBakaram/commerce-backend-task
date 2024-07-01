package example.com.plugins

import example.com.util.ErrorConstant.CLIENT_SERIALIZATION_ERR_MSG
import example.com.util.ErrorConstant.INVALID_REQUEST_STRUCTURE_ERR_MSG
import example.com.util.respond
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.BadRequestException
import io.ktor.server.plugins.statuspages.StatusPages
import kotlinx.serialization.SerializationException
import org.slf4j.LoggerFactory

fun Application.configureStatusPages() {
    val log = LoggerFactory.getLogger(javaClass)
    val errorLogPrefix = "error occurred with {}"

    install(StatusPages) {
        exception<Throwable> { call, cause ->
            log.error(errorLogPrefix, cause.message)
            call.respond {
                status = HttpStatusCode.InternalServerError
                errorMessage = "Error: $cause"
            }
        }

        exception<BadRequestException> { call, cause ->
            log.error(errorLogPrefix, cause.message)
            call.respond {
                status = HttpStatusCode.BadRequest
                errorMessage = INVALID_REQUEST_STRUCTURE_ERR_MSG
            }
        }

        exception<SerializationException> { call, cause ->
            log.error(errorLogPrefix, cause.localizedMessage)
            call.respond {
                status = HttpStatusCode.ExpectationFailed
                data = cause.localizedMessage
                errorMessage = CLIENT_SERIALIZATION_ERR_MSG
            }
        }
    }
}
