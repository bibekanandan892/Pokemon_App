package com.bibek.pokemonapp.presentation.screen.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bibek.pokemonapp.domain.usecase.GetPokemonDetailsUseCase
import com.bibek.pokemonapp.utils.Toaster
import com.bibek.pokemonapp.utils.connectivity.ConnectionState
import com.bibek.pokemonapp.utils.connectivity.ConnectivityObserver
import com.bibek.pokemonapp.utils.navigation.Navigator
import com.bibek.pokemonapp.utils.network.collectResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val toaster: Toaster,
    private val navigator: Navigator,
    private val getPokemonDetailsUseCase: GetPokemonDetailsUseCase,
    private val connectivityObserver: ConnectivityObserver
) : ViewModel() {
    private val _uiState = MutableStateFlow(PokemonDetailsState())
    val uiState get() = _uiState.asStateFlow()
    fun onEvent(event: PokemonDetailsEvent) {
        viewModelScope.launch {
            when (event) {
                is PokemonDetailsEvent.GetPokemonDetails -> {
                    if (uiState.value.id == null) {
                        getPokemonDetails(event.pokemonId)
                    }
                }
                PokemonDetailsEvent.NavigateBack -> navigator.back()
            }
        }
    }

    private suspend fun getPokemonDetails(pokemonId: String) {
        if(connectivityObserver.currentConnectionState == ConnectionState.Available){
            getPokemonDetailsUseCase.invoke(pokemonId).collectResponse(onSuccess = {
                _uiState.update { uiState ->
                    uiState.copy(
                        id = pokemonId,
                        image = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${it?.id}.png"
                        , name = it?.name?:"",
                        types = it?.types?: listOf(),
                        stats = it?.stats?: listOf(),
                        height = it?.height?.toInt()?:0,
                        weight = it?.weight?.toInt()?:0
                    )
                }
            }, onLoading = {
                _uiState.update { uiState -> uiState.copy(isPokemonDetailsLoading = it) }
            }, onError = {
                _uiState.update { uiState ->
                    uiState.copy(
                        name = "", image = "",
                    )
                }
                toaster.error(it)
            })
        }else{
            toaster.error("No Internet Connection")
        }
    }
}