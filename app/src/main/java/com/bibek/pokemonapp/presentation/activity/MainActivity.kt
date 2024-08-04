package com.bibek.pokemonapp.presentation.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bibek.pokemonapp.presentation.componets.ConnectivityStatus
import com.bibek.pokemonapp.presentation.navigation.SetupNavGraph
import com.bibek.pokemonapp.presentation.theme.ColorBackground
import com.bibek.pokemonapp.utils.Toaster
import com.bibek.pokemonapp.utils.navigation.Destination
import com.bibek.pokemonapp.utils.navigation.Navigator
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var toaster: Toaster

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navGraphController = rememberNavController()
            val mainViewModel: MainViewModel = hiltViewModel()
            val systemUiController = rememberSystemUiController()
            systemUiController.setSystemBarsColor(color = ColorBackground)
            val isConnectivityAvailable by mainViewModel.isConnectivityAvailable
            NavigationSetup(navGraphController)
            ToasterSetup()
            Scaffold(modifier = Modifier.fillMaxSize() .background(ColorBackground).statusBarsPadding()) { innerPadding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    isConnectivityAvailable?.let {
                        ConnectivityStatus(it)
                    }
                    SetupNavGraph(
                        startDestination = Destination.HOME.name,
                        navController = navGraphController
                    )
                }
            }

        }
    }

    @Composable
    private fun ToasterSetup() {
        LaunchedEffect(key1 = true) {
            toaster.errorFlow.collect {
                Toast.makeText(this@MainActivity, it, Toast.LENGTH_SHORT).show()
            }
        }
        LaunchedEffect(key1 = true) {
            toaster.successFlow.collectLatest {
                Toast.makeText(this@MainActivity, it, Toast.LENGTH_SHORT).show()
            }
        }
    }

    @Composable
    private fun NavigationSetup(navGraphController: NavHostController) {
        LaunchedEffect(key1 = true) {
            navigator.actions.collectLatest { action ->
                when (action) {
                    Navigator.Action.Back -> {
                        navGraphController.popBackStack()
                    }
                    is Navigator.Action.Navigate -> {
                        if (navGraphController.currentDestination?.route != action.destination) {
                            navGraphController.navigate(
                                route = action.destination, builder = action.navOptions
                            )
                        }
                    }
                }
            }
        }
    }
}
