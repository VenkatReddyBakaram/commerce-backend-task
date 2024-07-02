package com.tdl.model

import kotlinx.serialization.Serializable

@Serializable
data class CommentsDTO(
    val postId: Int,
    val id: Int,
    val name: String,
    val email: String,
    val body: String
)

