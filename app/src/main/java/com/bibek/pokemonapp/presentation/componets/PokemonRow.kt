package com.bibek.pokemonapp.presentation.componets

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.bibek.pokemonapp.R
import com.bibek.pokemonapp.domain.model.pokemon_list.Pokemon
import com.bibek.pokemonapp.presentation.theme.ColorLazyGridItem
import com.bibek.pokemonapp.presentation.theme.ColorPrimary

@ExperimentalAnimationApi
@Composable
fun PokemonRow(
    index: Int = 0,
    pokemon: Pokemon,
    height : Dp = 230.dp,
    onItemClick: (String) -> Unit = {}
) {
    val painter = rememberAsyncImagePainter(pokemon.image)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable {
                onItemClick(pokemon.id)
            },
        shape = RoundedCornerShape(corner = CornerSize(26.dp)),
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .height(height)
                    .background(ColorLazyGridItem[index % ColorLazyGridItem.size]),
                contentAlignment = Alignment.BottomStart,
            ) {
                Image(
                    painter = painter,
                    contentDescription = stringResource(R.string.pokemon_id),
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(15.dp)),
                    contentScale = ContentScale.Fit
                )
                if (painter.state is AsyncImagePainter.State.Loading) {
                    Box(
                        modifier = Modifier
                            .height(450.dp)
                            .fillMaxWidth(), contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = ColorPrimary)
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Black.copy(alpha = 0.7f), Color.Transparent
                                ), startY = Float.POSITIVE_INFINITY, endY = 0.5f
                            ), shape = RoundedCornerShape(15.dp)
                        ), contentAlignment = Alignment.BottomStart
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "#${pokemon.id} ${pokemon.name}",
                            fontStyle = FontStyle.Normal,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White,
                            fontSize = TextUnit(4f, TextUnitType.Em),
                            modifier = Modifier.padding(start = 10.dp)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Preview(showSystemUi = true)
@Composable
fun PokemonRowUI() {
    PokemonRow(pokemon = Pokemon(name = "long time coming", image = "", id = ""))
}