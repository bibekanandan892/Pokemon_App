package com.bibek.pokemonapp.presentation.componets

import androidx.annotation.RawRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@Suppress("DEPRECATION")
@Composable
fun LottieAnimation(
    @RawRes resId : Int,
    modifier: Modifier = Modifier,
    itrations : Int = LottieConstants.IterateForever,
    restartOnPlay : Boolean = false
){
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(resId))
    val progress by animateLottieCompositionAsState(composition = composition,
        iterations = itrations,
        restartOnPlay = restartOnPlay)
    LottieAnimation( composition,progress, modifier)
}
