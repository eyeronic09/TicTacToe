package com.example.tictactoe.HomeScreen.domain

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


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
    fun onMoves(index : Int) {
        val ticTacToeState = _uiState.value
        if (ticTacToeState.gameStatus == GameStatus.IsRunning ) {
            val newBoard = ticTacToeState.board.toMutableList().apply {
                set(
                    index, when (ticTacToeState.currentPlayer) {
                        is Player.x -> Player.x.symbol
                        is Player.o -> Player.o.symbol
                        Player.emp -> Player.emp.symbol
                    }
                )
            }
            _uiState.update { state ->
                state.copy(
                    board = newBoard,
                    currentPlayer = if (state.currentPlayer == Player.x) Player.o else Player.x
                )
            }

        }
    }

}