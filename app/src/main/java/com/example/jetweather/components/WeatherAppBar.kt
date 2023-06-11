package com.example.jetweather.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherAppBar(
    modifier: Modifier = Modifier,
    title: String = "Rewari",
    isMainScreen: Boolean = true,
    elevation: Dp = 0.dp,
    navController: NavController,
    onSearchClicked:() -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Text(title)
        },
        actions = {

            if (isMainScreen) {
                IconButton(onClick = onSearchClicked) {
                    Icon(imageVector = Icons.Default.Search, "Search Icon")
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.MoreVert, "Options")
                }
            }
        },

        modifier = modifier,
        navigationIcon = {
            if (isMainScreen) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.FavoriteBorder, "Favorite Icon")
                }
            } else {
                IconButton(
                    onClick = {
                        navController.navigateUp()
                    }
                ) {
                    Icon(imageVector = Icons.Default.ArrowBack, "Back Arrow Icon")
                }
            }
        }

    )
}