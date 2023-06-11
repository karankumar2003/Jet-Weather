package com.example.jetweather.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@Composable
fun SearchScreen() {
    SearchField(placeholder = "Rewari",
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        onSearch = {}
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchField(
    onSearch: () -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    maxLines: Int = 1,
    keyboardOptions: KeyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
    keyboardActions: KeyboardActions = KeyboardActions(onSearch = {
        onSearch()
    })
) {
    val searchState = rememberSaveable {
        mutableStateOf("")
    }
    OutlinedTextField(
        modifier = modifier,
        value = searchState.value,
        onValueChange = {
            searchState.value = it
        },
        placeholder = {
            Text(placeholder)
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                modifier.clickable {
                    onSearch()
                })
        },
        maxLines = maxLines,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions
    )
}