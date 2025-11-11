package com.example.tictactoe.HomeScreen.domain

import android.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

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
    var UiState by mutableStateOf(TTTState())
        private set


    fun onEvent(){

    }
}