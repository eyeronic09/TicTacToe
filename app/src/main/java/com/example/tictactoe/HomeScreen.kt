package com.example.tictactoe

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tictactoe.HomeScreen.Component.AlertBox
import com.example.tictactoe.HomeScreen.Component.TurnInfo
import com.example.tictactoe.HomeScreen.Component.BoardGrid
import com.example.tictactoe.HomeScreen.domain.GameStatus
import com.example.tictactoe.HomeScreen.domain.TicTacViewModel
import com.example.tictactoe.ui.theme.TicTacToeTheme


@Composable
fun HomeScreen(viewModel: TicTacViewModel = viewModel()) {
    val uiState by viewModel.uiStatus.collectAsStateWithLifecycle()
    Box(
        modifier = Modifier.fillMaxSize()

    ) {
        val openDialog = remember { mutableStateOf(false) }
        LaunchedEffect(uiState.gameStatus) {
            openDialog.value = uiState.gameStatus != GameStatus.IsRunning
        }
        Image(
            painter = painterResource(id = R.drawable.blue),
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        // Main Content
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            TurnInfo(uiState.currentPlayer.symbol)
            Spacer(modifier = Modifier.height(16.dp))
            BoardGrid(
                uiState.board,
                onCellClicked = { viewModel.onMoves(it) },
                indexToColor = uiState.patternNumber ?: emptyList(),
            )
        }
        if (openDialog.value){
            AlertBox(
                uiState.gameStatus,
                onDismiss = {
                    viewModel.resetGame()
                    openDialog.value = false
                })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    TicTacToeTheme {
        val vm: TicTacViewModel = viewModel()
        HomeScreen(viewModel = vm)
    }
}
