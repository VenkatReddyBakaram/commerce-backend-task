package example.com.model

data class CommentsDTO(
    val postId: Int,
    val id: Int,
    val name: String,
    val email: String,
    val body: String
)

