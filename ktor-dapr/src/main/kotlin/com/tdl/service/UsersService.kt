package com.tdl.service

import com.tdl.model.UserResponseDTO
import com.tdl.model.UserDto
import com.tdl.model.PostDTO
import com.tdl.model.CommentsDTO
import com.tdl.plugins.ConfigureClient
import com.tdl.plugins.ConfigureJedisClient.redisClient
import com.tdl.repository.UsersRepository
import com.tdl.util.getKoinInstance
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.slf4j.LoggerFactory

class UsersService {
    private val log = LoggerFactory.getLogger(javaClass)
    private val usersRepository = getKoinInstance<UsersRepository>()

    suspend fun getUserDetails(id: String?): UserResponseDTO {
        return if (id != null) {
            val jedisResponse = redisClient.get("$id")
            if (jedisResponse == null)
                usersRepository.getUserDetails(id)
            else {
                log.info("Jedis Response --> $id")
                Json.decodeFromString<UserResponseDTO>(jedisResponse)
            }
        } else {
            val userResponse =
                ConfigureClient.client.get("https://jsonplaceholder.typicode.com/users").body<List<UserDto>>()

            val postResponse =
                ConfigureClient.client.get("https://jsonplaceholder.typicode.com/posts").body<List<PostDTO>>()

            val commentsResponse =
                ConfigureClient.client.get("https://jsonplaceholder.typicode.com/comments").body<List<CommentsDTO>>()

            commentsResponse.forEach { comment ->
                val userList = userResponse.filter { it.id == comment.id }
                val postList = postResponse.filter { it.id == comment.id }
                val commentsList = listOf(comment)
                val combinedResponse = UserResponseDTO(userList, postList, commentsList)
                redisClient.set("${comment.id}", Json.encodeToString(combinedResponse))
            }

            UserResponseDTO(
                userResponse,
                postResponse,
                commentsResponse
            ).also {
                val check = usersRepository.getUserData()?.equals(it)
                if (check == false || check == null)
                    usersRepository.saveUsersDetails(it)
            }
        }
    }
}
