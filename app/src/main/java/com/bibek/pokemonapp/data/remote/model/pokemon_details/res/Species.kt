package com.bibek.pokemonapp.data.remote.model.pokemon_details.res


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Species(
    @SerialName("name")
    val name: String? = null,
    @SerialName("url")
    val url: String? = null
)