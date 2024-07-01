package example.com.plugins

import example.com.route.configureUsersRouting
import io.ktor.server.application.Application

fun Application.configureRouting() {
    configureUsersRouting()
}
