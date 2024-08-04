package com.bibek.pokemonapp.domain.usecase

import com.bibek.pokemonapp.data.remote.model.pokemon_details.res.PokemonDetailsResponseDto
import com.bibek.pokemonapp.domain.repository.PokemonRepository
import com.bibek.pokemonapp.utils.network.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPokemonDetailsUseCase @Inject constructor(private val pokemonRepository: PokemonRepository) {
    operator fun invoke(pokemonId : String): Flow<NetworkResult<PokemonDetailsResponseDto>> {
        return pokemonRepository.getPokemonDetails(pokemonId)
    }
}