package com.project.presentation.my

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun MyRoute(
    viewModel: MyViewModel = hiltViewModel()
) {

    MyScreen()
}

@Composable
fun MyScreen() {

}