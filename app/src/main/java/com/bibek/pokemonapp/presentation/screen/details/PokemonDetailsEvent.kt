package com.bibek.pokemonapp.presentation.screen.details

sealed interface PokemonDetailsEvent {
    data class GetPokemonDetails(val pokemonId: String) : PokemonDetailsEvent
    data object NavigateBack : PokemonDetailsEvent
}
