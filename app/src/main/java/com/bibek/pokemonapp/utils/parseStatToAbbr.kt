package com.bibek.pokemonapp.utils

import androidx.compose.ui.graphics.Color
import com.bibek.pokemonapp.data.remote.model.pokemon_details.res.Stat
import com.bibek.pokemonapp.presentation.theme.AtkColor
import com.bibek.pokemonapp.presentation.theme.DefColor
import com.bibek.pokemonapp.presentation.theme.HPColor
import com.bibek.pokemonapp.presentation.theme.SpAtkColor
import com.bibek.pokemonapp.presentation.theme.SpDefColor
import com.bibek.pokemonapp.presentation.theme.SpdColor

fun parseStatToAbbr(stat: Stat): String {
    return when(stat.stat?.name?.lowercase()) {
        "hp" -> "HP"
        "attack" -> "Atk"
        "defense" -> "Def"
        "special-attack" -> "SpAtk"
        "special-defense" -> "SpDef"
        "speed" -> "Spd"
        else -> ""
    }
}
fun parseStatToColor(stat: Stat): Color {
    return when(stat.stat?.name?.lowercase()) {
        "hp" -> HPColor
        "attack" -> AtkColor
        "defense" -> DefColor
        "special-attack" -> SpAtkColor
        "special-defense" -> SpDefColor
        "speed" -> SpdColor
        else -> Color.White
    }
}