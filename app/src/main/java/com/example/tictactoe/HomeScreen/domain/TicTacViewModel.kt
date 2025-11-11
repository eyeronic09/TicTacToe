package com.example.tictactoe.HomeScreen.domain

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


sealed class Player(val symbol : String) {
    object x : Player("X")
    object o : Player("O")
    object emp : Player(" ")

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
    val gameStatus : GameStatus = GameStatus.IsRunning,
    val gameResult: GameStatus? = null
)

class TicTacViewModel:ViewModel(){

    private val _uiState = MutableStateFlow(TTTState())
    val uiStatus : StateFlow<TTTState> = _uiState.asStateFlow()

    fun onMoves(index : Int) {
        val ticTacToeState = _uiState.value
        Log.d("onMove" , ticTacToeState.toString())
        if (ticTacToeState.gameStatus == GameStatus.IsRunning) {
            val winner = checkWinner(ticTacToeState.board)
           val newGameResult =  when(winner){
                 "X" ->  GameStatus.XWins
                 "O" ->  GameStatus.OWins
                "Draw" -> GameStatus.Draw
                else -> null
            }

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
                    currentPlayer = if (state.currentPlayer == Player.x) Player.o else Player.x,
                    gameResult = newGameResult

                )

            }
        }
    }
    fun checkWinner(board: List<String?>)  : String? {
        val winPatterns = listOf(
            listOf(0, 1, 2),
            listOf(3, 4, 5),
            listOf(6, 7, 8),
            listOf(0, 3, 6),
            listOf(1, 4, 7),
            listOf(2, 5, 8),
            listOf(0, 4, 8),
            listOf(2, 4, 6)
        )
        for (pattern in winPatterns){
            val(a, b , c ) = pattern
            if (board[a] != " " && board[a] == board[b] && board[a] == board[c]) {
                return (board[a])

            }
        }
        return "Draw"
    }
}