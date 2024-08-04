package com.bibek.pokemonapp.presentation.componets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalConfiguration
import com.bibek.pokemonapp.utils.ScreenOrientation
import android.content.res.Configuration
import androidx.compose.runtime.CompositionLocalProvider

val LocalOrientation = staticCompositionLocalOf { ScreenOrientation.Undefined }
@Composable
fun ProvideOrientation(content: @Composable () -> Unit) {
    val configuration = LocalConfiguration.current

    val orientation = when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> ScreenOrientation.Landscape
        Configuration.ORIENTATION_PORTRAIT -> ScreenOrientation.Portrait
        else -> ScreenOrientation.Undefined
    }

    CompositionLocalProvider(LocalOrientation provides orientation) {
        content()
    }
}