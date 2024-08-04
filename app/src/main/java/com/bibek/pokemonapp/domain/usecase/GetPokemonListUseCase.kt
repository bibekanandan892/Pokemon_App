package com.bibek.pokemonapp.domain.usecase

import androidx.paging.PagingData
import com.bibek.pokemonapp.domain.model.pokemon_list.Pokemon
import com.bibek.pokemonapp.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPokemonListUseCase @Inject constructor(private val pokemonRepository: PokemonRepository) {
    operator fun invoke(): Flow<PagingData<Pokemon>>  {
        return pokemonRepository.getPokemonList()
    }
}