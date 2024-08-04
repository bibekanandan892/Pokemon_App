package com.bibek.pokemonapp.presentation.screen.home

sealed class HomeEvent {
    data class NavigateToPokemonDetails(val pokemonId : String) : HomeEvent()
    data class OnQueryChange(val query: String) : HomeEvent()
}