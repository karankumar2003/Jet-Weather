package com.example.jetweather.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun CustomDialog(
    setShowDialog: (Boolean) -> Unit,
    buttonOnClick: (String) -> Unit
) {


    Dialog(onDismissRequest = { setShowDialog(false) }) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .clip(
                    RoundedCornerShape(16.dp)
                )
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(5.dp)

        ) {
            val cityState = remember {
                mutableStateOf("")
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text("Enter Home City")

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(value = cityState.value, onValueChange = {
                cityState.value = it
            })

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {
                    buttonOnClick(cityState.value)

                }
            ) {
                Text("Save")
            }


        }
    }
}