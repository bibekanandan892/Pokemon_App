package com.bibek.pokemonapp.data.remote.model.pokemon_list.res


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonItem(
    @SerialName("name")
    val name: String? = null,
    @SerialName("url")
    val url: String? = null
)