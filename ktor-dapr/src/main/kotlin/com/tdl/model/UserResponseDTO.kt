package com.tdl.model

import kotlinx.serialization.Serializable

@Serializable
data class UserResponseDTO(
    var userResponse: List<UserDto>,
    var postResponse: List<PostDTO>,
    var commentsResponse: List<CommentsDTO>
)
