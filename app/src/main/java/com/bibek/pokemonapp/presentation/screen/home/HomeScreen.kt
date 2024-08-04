package com.bibek.pokemonapp.presentation.screen.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.bibek.pokemonapp.domain.model.pokemon_list.Pokemon
import com.bibek.pokemonapp.presentation.componets.PokemonRow
import com.bibek.pokemonapp.presentation.componets.SearchTopBar
import com.bibek.pokemonapp.presentation.componets.ShimmerPokemonItem
import com.bibek.pokemonapp.presentation.theme.ColorBackground

@Composable
fun HomeScreen(
    uiState: HomeState,
    onEvent: (HomeEvent) -> Unit = {}
) {
    val pokemonList = uiState.pokemonPager.collectAsLazyPagingItems()
    HomeUI(
        query = uiState.query,
        pokemonList = pokemonList,
        onEvent = onEvent,
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HomeUI(

    pokemonList: LazyPagingItems<Pokemon>,
    onEvent: (HomeEvent) -> Unit,
    query: String
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            SearchTopBar(query, onEvent)
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(ColorBackground)
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            if (pokemonList.loadState.refresh is LoadState.Loading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(10.dp), color = Color.Black
                )
            } else if (pokemonList.itemCount == 0) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "No Pokemon Available")
                }
            } else {
                LazyVerticalGrid(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(ColorBackground)
                        .padding(horizontal = 5.dp)
                        .padding(top = 5.dp),
                    columns = GridCells.Adaptive(170.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    items(pokemonList.itemCount) { index ->
                        val pokemon = pokemonList[index]
                        if (pokemon != null) {
                            PokemonRow(index = index, pokemon = pokemon, onItemClick = { id ->
                                onEvent(HomeEvent.NavigateToPokemonDetails(id))
                            })
                        }
                        Spacer(modifier = Modifier.height(20.dp))

                    }
                    item {
                        if (pokemonList.loadState.append is LoadState.Loading) {
                            ShimmerPokemonItem()
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeUiPreview(modifier: Modifier = Modifier) {
    HomeScreen(uiState = HomeState())
}
