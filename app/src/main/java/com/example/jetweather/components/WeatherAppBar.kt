package com.example.jetweather.components

import android.widget.Toast
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jetweather.model.Favorite
import com.example.jetweather.screens.WeatherScreens
import com.example.jetweather.viewModel.FavoriteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherAppBar(
    modifier: Modifier = Modifier,
    title: String = "Rewari",
    isMainScreen: Boolean = true,
    favoriteViewModel:FavoriteViewModel = hiltViewModel(),
    navController: NavController,
    onSearchClicked: (() -> Unit)?=null
) {
    val expanded = remember {
        mutableStateOf(false)
    }

    CenterAlignedTopAppBar(


        title = {
            Text(title)
        },
        actions = {

            if (isMainScreen) {
                IconButton(onClick = onSearchClicked!!) {
                    Icon(imageVector = Icons.Default.Search, "Search Icon")
                }
                IconButton(onClick = { expanded.value = !expanded.value }) {

                    Icon(imageVector = Icons.Default.MoreVert, "Options")

                    DropdownMenu(expanded = expanded.value,
                        onDismissRequest = { expanded.value = false }
                    )
                    {
                        DropdownMenuItem(text = { Text("Favorite") }, onClick = {
                            navController.navigate(
                                WeatherScreens.FavoriteScreen.name
                            )
                        },
                            leadingIcon = {
                                Icon(Icons.Default.Favorite, contentDescription = "Favorites")
                            })

                        DropdownMenuItem(text = { Text("About") }, onClick = {
                            navController.navigate(
                                WeatherScreens.AboutScreen.name
                            )
                        },
                            leadingIcon = {
                                Icon(Icons.Default.Info, contentDescription = "About")
                            })

                        DropdownMenuItem(text = { Text("Settings") }, onClick = {
                            navController.navigate(
                                WeatherScreens.SettingScreen.name
                            )
                        },
                            leadingIcon = {
                                Icon(Icons.Default.Settings, contentDescription = "Settings")
                            })
                    }

                }
            }
        },

        modifier = modifier,
        navigationIcon = {
            val favList = favoriteViewModel.favoriteList.collectAsState().value
            val context = LocalContext.current
            if (isMainScreen) {
                if(favList.contains(Favorite(title))) {


                    IconButton(onClick = {
                        favoriteViewModel.deleteFavorite(Favorite(title))
                        Toast.makeText(context,"Removed From Favorites",Toast.LENGTH_SHORT).show()
                    }) {
                        Icon(imageVector = Icons.Default.Favorite, "Remove from favorites")
                    }
                }else{
                    IconButton(onClick = {
                        favoriteViewModel.upsertFavorite(Favorite(title))
                        Toast.makeText(context,"Added to favorites",Toast.LENGTH_SHORT).show()

                    }) {
                        Icon(imageVector = Icons.Default.FavoriteBorder, "Add to favorites")
                    }
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