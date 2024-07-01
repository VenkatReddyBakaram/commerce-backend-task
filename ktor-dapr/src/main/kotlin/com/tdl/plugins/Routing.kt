package com.tdl.plugins

import com.tdl.route.configureUsersRouting
import io.ktor.server.application.Application

fun Application.configureRouting() {
    configureUsersRouting()
}
