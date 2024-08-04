package com.bibek.pokemonapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bibek.pokemonapp.presentation.screen.details.DetailsScreen
import com.bibek.pokemonapp.presentation.screen.details.DetailsViewModel
import com.bibek.pokemonapp.presentation.screen.details.PokemonDetailsEvent
import com.bibek.pokemonapp.presentation.screen.home.HomeScreen
import com.bibek.pokemonapp.presentation.screen.home.HomeViewModel
import com.bibek.pokemonapp.utils.navigation.Destination

@Composable
fun SetupNavGraph(
    startDestination: String,
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(route = Destination.HOME.name){
            val homeViewModel : HomeViewModel = hiltViewModel()
            val uiState by homeViewModel.uiState.collectAsStateWithLifecycle()
            HomeScreen(uiState = uiState, onEvent = homeViewModel::onEvent)
        }
        composable(
            route = Destination.POKEMON_DETAILS.name+"/{pokemonId}"
        ) { backStackEntry->
            val pokemonId = backStackEntry.arguments?.getString("pokemonId").toString()
            val detailsViewModel : DetailsViewModel = hiltViewModel()
            val uiState by detailsViewModel.uiState.collectAsStateWithLifecycle()
            LaunchedEffect(key1 = true) {
                detailsViewModel.onEvent(PokemonDetailsEvent.GetPokemonDetails(pokemonId = pokemonId))
            }
            DetailsScreen(uiState = uiState, onEvent = detailsViewModel::onEvent)
        }
    }
}
