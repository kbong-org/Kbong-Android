package com.project.presentation.my

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun MyRoute(
    viewModel: MyViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val myTeamType = MyTeamType.fromTypeData(state.userInfoContent.myTeam.code)
    MyScreen(
        state = state,
        myTeamType = myTeamType
    )
}

@Composable
fun MyScreen(
    state: MyContract.MyViewState,
    myTeamType: MyTeamType
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(myTeamType.backgroundColor)
        ) { }
    }
}