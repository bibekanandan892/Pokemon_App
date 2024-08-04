package com.bibek.pokemonapp.data.remote.model.pokemon_details.res


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VersionGroupDetail(
    @SerialName("level_learned_at")
    val levelLearnedAt: Int? = null,
    @SerialName("move_learn_method")
    val moveLearnMethod: MoveLearnMethod? = null,
    @SerialName("version_group")
    val versionGroup: VersionGroup? = null
)