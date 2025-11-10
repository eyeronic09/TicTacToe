package com.example.tictactoe.HomeScreen.domain

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class TicTacToeState(
    // 3x3 game board, null means empty, 'X' or 'O' for moves
    val board: List<List<Player?>> = List(3) { List(3) { null } },
    val currentPlayer: Player = Player.X,  // X starts first
    val gameStatus: GameStatus = GameStatus.Playing,
    val winningCombination: List<Pair<Int, Int>>? = null
) {
    // Player can be either X or O
    enum class Player {
        X, O
    }

    // Possible game statuses
    enum class GameStatus {
        Playing,  // Game is in progress
        Draw,     // Game ended in a draw
        Won       // Game was won by a player
    }
}
class TicTacViewModel : ViewModel() {
    private val _state = MutableStateFlow<TicTacToeState>(TicTacToeState())
    val state : StateFlow<TicTacToeState> = _state.asStateFlow()



}