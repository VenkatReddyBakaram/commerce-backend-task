package com.tdl.model

import kotlinx.serialization.Serializable

@Serializable
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

@Serializable
data class AddressDTO(
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
    val geo: GeoDTO
)

@Serializable
data class GeoDTO(
    val lat: String,
    val lng: String
)

@Serializable
data class CompanyDTO(
    val name: String,
    val catchPhrase: String,
    val bs: String
)
