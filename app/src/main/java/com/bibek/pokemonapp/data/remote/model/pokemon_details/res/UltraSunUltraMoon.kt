package com.bibek.pokemonapp.data.remote.model.pokemon_details.res


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UltraSunUltraMoon(
    @SerialName("front_default")
    val frontDefault: String? = null,
    @SerialName("front_female")
    val frontFemale: String? = null,
    @SerialName("front_shiny")
    val frontShiny: String? = null,
    @SerialName("front_shiny_female")
    val frontShinyFemale: String? = null
)