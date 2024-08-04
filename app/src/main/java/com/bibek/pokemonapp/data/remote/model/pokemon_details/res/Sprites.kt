package com.bibek.pokemonapp.data.remote.model.pokemon_details.res


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Sprites(
    @SerialName("back_default")
    val backDefault: String? = "",
    @SerialName("back_female")
    val backFemale: String? =null,
    @SerialName("back_shiny")
    val backShiny: String? = "",
    @SerialName("back_shiny_female")
    val backShinyFemale: String? = null,
    @SerialName("front_default")
    val frontDefault: String? = "",
    @SerialName("front_female")
    val frontFemale: String? = null,
    @SerialName("front_shiny")
    val frontShiny: String? = "",
    @SerialName("front_shiny_female")
    val frontShinyFemale: String? = null,
    @SerialName("other")
    val other: Other? = Other(),
    @SerialName("versions")
    val versions: Versions? = Versions()
)