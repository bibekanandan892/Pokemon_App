package com.bibek.pokemonapp.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.bibek.pokemonapp.domain.model.pokemon_list.Pokemon
import com.bibek.pokemonapp.domain.usecase.GetPokemonListUseCase
import com.bibek.pokemonapp.utils.connectivity.ConnectionState
import com.bibek.pokemonapp.utils.connectivity.ConnectivityObserver
import com.bibek.pokemonapp.utils.navigation.Destination
import com.bibek.pokemonapp.utils.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPokemonListUseCase: GetPokemonListUseCase,
    private val navigator: Navigator,
    private val connectivityObserver: ConnectivityObserver
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeState())
    val uiState get() = _uiState.asStateFlow()
    private val _queryFlow = MutableSharedFlow<String>(replay = 1)
    init {
        getPokemon()
        viewModelScope.launch {
            _queryFlow.emit("")
        }
        viewModelScope.launch {
            connectivityObserver.connectionState.collectLatest {
//                if(it is ConnectionState.Available){
//                    delay(2000)
//                    getPokemon()
//                }
            }
        }
    }
    private fun getPokemon() {
        _uiState.update { uiState ->
            val pokemonPager: Flow<PagingData<Pokemon>> =
                getPokemonListUseCase.invoke()
                    .cachedIn(viewModelScope)
                    .combine(_queryFlow) { pagingData, query ->
                        pagingData.filter { pokemon ->
                            query.isEmpty() || pokemon.name.contains(query, ignoreCase = true)
                        }
                    }
                    .cachedIn(viewModelScope)
            uiState.copy(pokemonPager = pokemonPager)
        }
    }
    fun onEvent(event: HomeEvent) {
        viewModelScope.launch {
            when (event) {
                is HomeEvent.NavigateToPokemonDetails -> {
                    navigator.navigate(destination = Destination.POKEMON_DETAILS.name + "/${event.pokemonId}")
                }
                is HomeEvent.OnQueryChange -> {
                    _queryFlow.emit(event.query)
                    _uiState.update { uiState -> uiState.copy(query = event.query) }
                }
            }
        }
    }
}