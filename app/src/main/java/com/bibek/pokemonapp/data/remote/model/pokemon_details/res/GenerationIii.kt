package com.bibek.pokemonapp.data.remote.model.pokemon_details.res


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenerationIii(
    @SerialName("emerald")
    val emerald: Emerald? = null,
    @SerialName("firered-leafgreen")
    val fireredLeafgreen: FireredLeafgreen? = null,
    @SerialName("ruby-sapphire")
    val rubySapphire: RubySapphire? = null
)