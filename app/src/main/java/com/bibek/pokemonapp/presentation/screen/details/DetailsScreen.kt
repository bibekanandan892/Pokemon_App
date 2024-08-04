package com.bibek.pokemonapp.presentation.screen.details

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bibek.pokemonapp.domain.model.pokemon_list.Pokemon
import com.bibek.pokemonapp.presentation.componets.PokemonDetailDataSection
import com.bibek.pokemonapp.presentation.componets.PokemonRow
import com.bibek.pokemonapp.presentation.componets.PokemonTypeSection
import com.bibek.pokemonapp.presentation.componets.ProgressIndicatorComponent
import com.bibek.pokemonapp.presentation.componets.TopBar
import com.bibek.pokemonapp.utils.parseStatToAbbr
import com.bibek.pokemonapp.utils.parseStatToColor
import kotlin.reflect.KFunction1

@Composable
fun DetailsScreen(
    uiState: PokemonDetailsState, onEvent: KFunction1<PokemonDetailsEvent, Unit>
) {
    PokemonDetailsUI(uiState, onEvent)
}

@Composable
private fun PokemonDetailsUI(
    uiState: PokemonDetailsState,
    onEvent: (PokemonDetailsEvent) -> Unit,
) {
    Scaffold(modifier = Modifier
        .fillMaxSize()
        .padding(top = 10.dp), topBar = {
        TopBar(
            if (uiState.id == null) "" else "#${uiState.id} ${uiState.name}",
            onBackClick = { onEvent(PokemonDetailsEvent.NavigateBack) })
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            if (uiState.isPokemonDetailsLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = Color.Black)
                }
            } else if (uiState.name.isNotEmpty()) {
                PokemonDetailsContent(uiState = uiState)
            } else {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "No Pokemon Details Found")
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun PokemonDetailsContent(uiState: PokemonDetailsState) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 450.dp)) {
            item {
                Column {
                    PokemonRow(
                        index = uiState.id?.toInt() ?: 0,
                        pokemon = Pokemon(
                            id = uiState.id.toString(),
                            image = uiState.image,
                            name = ""
                        ),
                        height = 400.dp
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    PokemonTypeSection(types = uiState.types)
                    PokemonDetailDataSection(pokemonHeight = uiState.height, pokemonWeight = uiState.weight)
                }

            }
            item {
                BaseStatsComponent(uiState)
            }
        }
    }
}

@Composable
private fun BaseStatsComponent(uiState: PokemonDetailsState) {
    Column {
        Text(
            modifier = Modifier.padding(
                horizontal = 10.dp, vertical = 10.dp
            ),
            text = "Base Stats",
            fontSize = TextUnit(6f, TextUnitType.Em),
            style = TextStyle(fontFamily = FontFamily.SansSerif),
            fontWeight = FontWeight.Bold
        )
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 200.dp),
            modifier = Modifier.height(550.dp), horizontalArrangement = Arrangement.Center
        ) {
            items(uiState.stats) {
                Box(modifier = Modifier.size(200.dp), contentAlignment = Alignment.Center) {
                    ProgressIndicatorComponent(
                        canvasSize = 200.dp, indicatorValue = it.baseStat ?: 0,
                        bigTextFontSize = 20.sp,
                        backgroundIndicatorStrokeWidth = 40f,
                        foregroundIndicatorStrokeWidth = 30f,
                        foregroundIndicatorColor = parseStatToColor(it),
                        smallText = parseStatToAbbr(it),
                        bigTextSuffix = "%"
                    )
                }
            }
        }
    }
}
