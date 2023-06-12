package com.example.jetweather.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.jetweather.components.WeatherAppBar
import com.example.jetweather.viewModel.FavoriteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(
    navController: NavHostController,
    favoriteViewModel: FavoriteViewModel = hiltViewModel()
) {
    Scaffold(topBar = {
        WeatherAppBar(
            navController = navController,
            title = "Favorite Cities",
            isMainScreen = false,
        )
    }
    ) {
        val favList = favoriteViewModel.favoriteList.collectAsState().value
        LazyColumn(
            Modifier.padding(it)
                .padding(horizontal=10.dp)

        ) {
            items(favList) { favoriteItem ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                        .clickable {
                            navController.navigate(WeatherScreens.MainScreen.name+"/${favoriteItem.city}")
                        }


                ) {
                    Text(favoriteItem.city, fontSize = 20.sp, modifier = Modifier.padding(top = 10.dp,bottom =10.dp,end=10.dp,start=30.dp))
                    Icon(Icons.Default.Delete, "delete", modifier = Modifier.padding(top = 10.dp,bottom =10.dp,end=30.dp,start=10.dp))
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }

}