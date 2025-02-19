package com.tdl.repository

import com.tdl.config.MongoConfigFactory
import com.tdl.model.UserResponseDTO
import com.tdl.model.UserDto
import com.tdl.model.PostDTO
import com.tdl.model.CommentsDTO
import org.litote.kmongo.div
import org.litote.kmongo.eq
import org.slf4j.LoggerFactory

class UsersRepository {
    private val log = LoggerFactory.getLogger(javaClass)

    private val userDetailsCollection =
        MongoConfigFactory.getDatabase().getCollection<UserResponseDTO>("user_details")

    suspend fun getUserData(): UserResponseDTO? {
        return userDetailsCollection.findOne()
    }

    suspend fun saveUsersDetails(userResponseDTO: UserResponseDTO) {
        userDetailsCollection.insertOne(userResponseDTO)
    }

    suspend fun getUserDetails(id: String): UserResponseDTO {
        log.info("Fetching data from DB --> $id")
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
