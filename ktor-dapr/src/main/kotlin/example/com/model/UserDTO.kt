package example.com.model

data class UserDto(
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val address: AddressDTO,
    val phone: String,
    val website: String,
    val company: CompanyDTO
)

data class AddressDTO(
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
    val geo: GeoDTO
)

data class GeoDTO(
    val lat: String,
    val lng: String
)

data class CompanyDTO(
    val name: String,
    val catchPhrase: String,
    val bs: String
)
