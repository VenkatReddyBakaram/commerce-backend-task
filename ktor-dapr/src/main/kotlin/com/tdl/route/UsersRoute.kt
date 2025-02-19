package com.tdl.route

import com.tdl.service.UsersService
import com.tdl.util.APIResponse
import com.tdl.util.respond
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.routing.routing
import io.ktor.server.routing.route
import io.ktor.server.routing.get
import org.koin.ktor.ext.inject

fun Application.configureUsersRouting() {
    val usersService by inject<UsersService>()

    routing {
        route("/userservice") {
            get("/userdetails") {
                call.respond(
                    APIResponse(
                        HttpStatusCode.OK,
                        usersService.getUserDetails(call.request.queryParameters["id"])
                    )
                )
            }
        }
    }
}
