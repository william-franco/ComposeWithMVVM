package br.com.williamfranco.composewithmvvm.src.features.users.models

import kotlinx.serialization.Serializable

@Serializable
data class UserModel(
    val id: Int? = null,
    val name: String? = null,
    val username: String? = null,
    val email: String? = null,
    val address: Address? = null,
    val phone: String? = null,
    val website: String? = null,
    val company: Company? = null,
)

@Serializable
data class Address(
    val street: String? = null,
    val suite: String? = null,
    val city: String? = null,
    val zipcode: String? = null,
    val geo: Geo? = null,
)

@Serializable
data class Geo(
    val lat: String? = null,
    val lng: String? = null,
)

@Serializable
data class Company(
    val name: String? = null,
    val catchPhrase: String? = null,
    val bs: String? = null,
)
