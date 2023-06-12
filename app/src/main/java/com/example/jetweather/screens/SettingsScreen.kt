package com.example.jetweather.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.jetweather.components.WeatherAppBar
import com.example.jetweather.viewModel.PreferencesViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navController: NavHostController,
    preferencesViewModel: PreferencesViewModel
) {
    val sheetState = rememberModalBottomSheetState()
    val defaultCity = preferencesViewModel.defaultCity.collectAsState().value


    Scaffold(
        topBar = {
            WeatherAppBar(navController = navController, isMainScreen = false, title = "Settings")
        }
    ) {
        val openBottomSheet = remember {
            mutableStateOf(false)
        }
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                val switchState = remember { mutableStateOf(false) }

                Text("Dynamic Theme", style = MaterialTheme.typography.titleMedium)
                Switch(checked = switchState.value, onCheckedChange = {
                    switchState.value = !switchState.value
                })
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        openBottomSheet.value = true
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text("Unit System", style = MaterialTheme.typography.titleMedium)

                Row(verticalAlignment = Alignment.CenterVertically) {

                    Text(text = defaultCity, modifier = Modifier.alpha(0.8f))

                    Icon(
                        Icons.Default.KeyboardArrowRight,
                        contentDescription = null,
                        modifier = Modifier.padding(start = 4.dp, end = 5.dp)
                    )
                }


            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text("Default City", style = MaterialTheme.typography.titleMedium)

                Row(verticalAlignment = Alignment.CenterVertically) {

                    Text(text = defaultCity, modifier = Modifier.alpha(0.8f))

                    Icon(
                        Icons.Default.KeyboardArrowRight,
                        contentDescription = null,
                        modifier = Modifier.padding(start = 4.dp, end = 5.dp)
                    )
                }


            }


        }

        if (openBottomSheet.value) {
            ModalBottomSheet(
                onDismissRequest = {
                    openBottomSheet.value = false
                },
                sheetState = sheetState,

                ) {

                SheetContent(preferencesViewModel, openBottomSheet,sheetState)

            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SheetContent(
    preferencesViewModel: PreferencesViewModel,
    openBottomSheet: MutableState<Boolean>,
    sheetState:SheetState
) {
    val scope = rememberCoroutineScope()
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
        Button(
            onClick = {
                preferencesViewModel.saveDefaultUnit("Imperial")
                scope.launch {
                    sheetState.hide()
                    openBottomSheet.value = false
                }

            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                "Imperial",
                fontSize = 20.sp
            )
        }
        Button(
            onClick = {
                preferencesViewModel.saveDefaultUnit("Metric")
                scope.launch {
                    sheetState.hide()
                    openBottomSheet.value = false
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                "Metric",
                fontSize = 20.sp
            )
        }
    }
}