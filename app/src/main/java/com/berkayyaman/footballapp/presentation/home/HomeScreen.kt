package com.berkayyaman.footballapp.presentation.home

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel

/**
 * Created by berkayyaman on 21,October,2024
 */

@Composable
fun HomeScreen() {

    val viewModel: HomeViewModel = hiltViewModel()
    val state = viewModel.viewStateFlow.collectAsState()


    Text(
        text = if (state.value.fixtures.isEmpty()){
            "henüz veri yok"
        }else{
            "${state.value.fixtures.size} kadar veri var"
        },
        style = MaterialTheme.typography.titleLarge
    )
}