package example.com.service

import example.com.model.UserResponseDTO
import example.com.model.UserDto
import example.com.model.PostDTO
import example.com.model.CommentsDTO
import example.com.plugins.ConfigureClient
import example.com.repository.UsersRepository
import example.com.util.getKoinInstance
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import org.slf4j.LoggerFactory

class UsersService {
    private val log = LoggerFactory.getLogger(javaClass)
    private val usersRepository = getKoinInstance<UsersRepository>()

    suspend fun getUserDetails(id: String?): UserResponseDTO {
        return if (id != null) {
            usersRepository.getUserDetails(id)
        } else {
            val userResponse = ConfigureClient.client.get("https://jsonplaceholder.typicode.com/users")
            log.info("userResponse === ${userResponse.bodyAsText()}")

            val postResponse = ConfigureClient.client.get("https://jsonplaceholder.typicode.com/posts")
            log.info("postResponse === ${postResponse.bodyAsText()}")

            val commentsResponse =
                ConfigureClient.client.get("https://jsonplaceholder.typicode.com/comments")
            log.info("commentsResponse === ${commentsResponse.bodyAsText()}")

            UserResponseDTO(
                userResponse.body<List<UserDto>>(),
                postResponse.body<List<PostDTO>>(),
                commentsResponse.body<List<CommentsDTO>>()
            ).also {
                val check = usersRepository.getUserData()?.equals(it)
                if (check == false || check == null)
                    usersRepository.saveUsersDetails(it)
            }
        }
    }
}
