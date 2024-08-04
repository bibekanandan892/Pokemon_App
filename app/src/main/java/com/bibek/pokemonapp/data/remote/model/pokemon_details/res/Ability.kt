package com.bibek.pokemonapp.data.remote.model.pokemon_details.res


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Ability(
    @SerialName("ability")
    val ability: AbilityX? = AbilityX(),
    @SerialName("is_hidden")
    val isHidden: Boolean? = false,
    @SerialName("slot")
    val slot: Int? = 0
)