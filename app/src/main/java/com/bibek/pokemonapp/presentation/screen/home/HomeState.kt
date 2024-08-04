package com.bibek.pokemonapp.presentation.screen.home

import androidx.paging.PagingData
import com.bibek.pokemonapp.data.remote.model.pokemon_details.res.Type
import com.bibek.pokemonapp.domain.model.pokemon_list.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class HomeState(
    val isLoading: Boolean = false,
    val query: String = "",
    val pokemonPager: Flow<PagingData<Pokemon>> = flowOf(),
    val types: List<Type> = listOf()
)
