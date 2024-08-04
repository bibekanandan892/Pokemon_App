package com.bibek.pokemonapp.domain.repository

import androidx.paging.PagingData
import com.bibek.pokemonapp.data.remote.model.pokemon_details.res.PokemonDetailsResponseDto
import com.bibek.pokemonapp.domain.model.pokemon_list.Pokemon
import com.bibek.pokemonapp.utils.network.NetworkResult
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun getPokemonList(
    ): Flow<PagingData<Pokemon>>
    fun getPokemonDetails(pokemonId : String): Flow<NetworkResult<PokemonDetailsResponseDto>>
}