package example.com.model

data class UserResponseDTO(
    var userResponse: List<UserDto>,
    var postResponse: List<PostDTO>,
    var commentsResponse: List<CommentsDTO>
)
