package example.com.repository

import example.com.config.MongoConfigFactory
import example.com.model.UserResponseDTO
import example.com.model.UserDto
import example.com.model.PostDTO
import example.com.model.CommentsDTO
import org.litote.kmongo.div
import org.litote.kmongo.eq

class UsersRepository {
    private val userDetailsCollection =
        MongoConfigFactory.getDatabase().getCollection<UserResponseDTO>("user_details")

    suspend fun getUserData(): UserResponseDTO? {
        return userDetailsCollection.findOne()
    }

    suspend fun saveUsersDetails(userResponseDTO: UserResponseDTO) {
        userDetailsCollection.insertOne(userResponseDTO)
    }

    suspend fun getUserDetails(id: String): UserResponseDTO {
        val userId = id.toInt()
        return UserResponseDTO(
            userDetailsCollection.findOne()?.userResponse?.filter {
                it.id == userId
            } ?: emptyList(),
            userDetailsCollection.findOne()?.postResponse?.filter {
                it.id == userId
            } ?: emptyList(),
            userDetailsCollection.findOne()?.commentsResponse?.filter {
                it.id == userId
            } ?: emptyList()
        )
    }
}
