package com.example.tictactoe.HomeScreen.domain

import android.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

sealed class Player(val symbol : String) {
    object x : Player("X")
    object o : Player("O")
    object emp : Player("_")
}
sealed class GameStatus {
    object IsRunning : GameStatus()
    object XWins : GameStatus()
    object OWins : GameStatus()
    object Draw : GameStatus()
}

data class TTTState(
    val board : List<String> = List(9) { index -> " " },
    val currentPlayer: Player = Player.x ,
    val gameStatus : GameStatus = GameStatus.IsRunning
)

class TicTacViewModel:ViewModel(){

    private val _uiState = MutableStateFlow(TTTState())
    val uiStatus : StateFlow<TTTState> = _uiState.asStateFlow()



}