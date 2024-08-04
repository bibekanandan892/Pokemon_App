package com.bibek.pokemonapp.data.remote.model.pokemon_details.res


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RedBlue(
    @SerialName("back_default")
    val backDefault: String? = null,
    @SerialName("back_gray")
    val backGray: String? = null,
    @SerialName("back_transparent")
    val backTransparent: String? = null,
    @SerialName("front_default")
    val frontDefault: String? = null,
    @SerialName("front_gray")
    val frontGray: String? = null,
    @SerialName("front_transparent")
    val frontTransparent: String? = null
)