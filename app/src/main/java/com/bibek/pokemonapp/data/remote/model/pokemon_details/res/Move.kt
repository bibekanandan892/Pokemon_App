package com.bibek.pokemonapp.data.remote.model.pokemon_details.res


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Move(
    @SerialName("move")
    val move: MoveX? = MoveX(),
    @SerialName("version_group_details")
    val versionGroupDetails: List<VersionGroupDetail>? = listOf()
)