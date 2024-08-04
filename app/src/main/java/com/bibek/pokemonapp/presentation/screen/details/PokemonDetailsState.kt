package com.bibek.pokemonapp.presentation.screen.details

import androidx.compose.runtime.Immutable
import com.bibek.pokemonapp.data.remote.model.pokemon_details.res.Stat
import com.bibek.pokemonapp.data.remote.model.pokemon_details.res.Type

@Immutable
data class PokemonDetailsState(
    val id : String? = null,
    val name: String = "",
    val image: String = "",
    val hour: Int = 0,
    val min: Int = 0,
    val isPokemonDetailsLoading : Boolean = false,
    val types: List<Type> = listOf(),
    val stats : List<Stat> = listOf(),
    val height : Int= 0,
    val weight : Int = 0,
)
