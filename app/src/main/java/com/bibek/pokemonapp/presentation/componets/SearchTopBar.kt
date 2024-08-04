package com.bibek.pokemonapp.presentation.componets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bibek.pokemonapp.R
import com.bibek.pokemonapp.presentation.screen.home.HomeEvent
import com.bibek.pokemonapp.presentation.theme.ColorBackground
import com.bibek.pokemonapp.presentation.theme.ColorTextFieldContainerDefault
import com.bibek.pokemonapp.presentation.theme.ColorTextFieldText
import com.bibek.pokemonapp.presentation.theme.ColorTextTitle
import com.bibek.pokemonapp.utils.ScreenOrientation

@Composable
fun SearchTopBar(
    query: String,
    onEvent: (HomeEvent) -> Unit,
) {
    val orientation = LocalOrientation.current
    val keyboardController = LocalSoftwareKeyboardController.current
    Row(
        Modifier
            .fillMaxWidth()
            .background(ColorBackground), horizontalArrangement = Arrangement.Center) {
        if(orientation == ScreenOrientation.Portrait){
            Column(modifier = Modifier
                .background(ColorBackground)
                .widthIn(
                    max = 500.dp
                )) {
                Logo()
                SearchBar(query, onEvent, keyboardController)
            }
        }else{
            Box (Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
                Box(modifier = Modifier.fillMaxWidth()){
                    Logo()
                }
                SearchBar(query, onEvent, keyboardController)
            }
        }
    }
}

@Composable
private fun SearchBar(
    query: String,
    onEvent: (HomeEvent) -> Unit,
    keyboardController: SoftwareKeyboardController?
) {
    Row(modifier = Modifier.widthIn(max=500.dp),verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
        TextField(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
            value = query,
            onValueChange = {
                onEvent.invoke(HomeEvent.OnQueryChange(it))
            },
            leadingIcon = {
                Icon(
                    modifier = Modifier.padding(horizontal = 10.dp),
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search", tint = ColorTextTitle
                )
            },
            placeholder = {
                Text(text = "Search...")
            },
            shape = RoundedCornerShape(20.dp),
            colors = TextFieldDefaults.textFieldColors(
                unfocusedLabelColor = ColorTextFieldText,
                focusedIndicatorColor = ColorTextFieldContainerDefault,
                unfocusedIndicatorColor = ColorTextFieldContainerDefault,
                disabledIndicatorColor = ColorTextFieldContainerDefault,
                backgroundColor = ColorTextFieldContainerDefault
            ), trailingIcon = {
                if (query.isNotEmpty()) {
                    Icon(
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .background(color = ColorTextTitle, RoundedCornerShape(50))
                            .padding(2.dp)
                            .clickable {
                                onEvent(HomeEvent.OnQueryChange(""))
                                keyboardController?.hide()
                            },
                        imageVector = Icons.Default.Close,
                        contentDescription = "Search",
                        tint = Color.White
                    )
                }
            }
        )
    }
}

@Composable
private fun Logo() {
    Image(
        painter = painterResource(id = R.drawable.ic_brand),
        contentDescription = "logo",
        modifier = Modifier.padding(10.dp)
    )
}