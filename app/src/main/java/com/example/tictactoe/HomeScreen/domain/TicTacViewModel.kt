package com.example.tictactoe.HomeScreen.domain

import android.graphics.Insets.add
import android.util.Log
import androidx.compose.ui.res.integerResource
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


sealed class Player(val symbol: String) {
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
    val board: List<String> = List(9) { " " },
    val currentPlayer: Player = Player.x,
    val gameStatus: GameStatus = GameStatus.IsRunning,
    val patternNumber: List<Int>? = null
)

class TicTacViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(TTTState())
    val uiStatus: StateFlow<TTTState> = _uiState.asStateFlow()

    fun onMoves(index: Int) {
        val ticTacToeState = _uiState.value
        Log.d("onMove", ticTacToeState.toString())
        if (ticTacToeState.gameStatus == GameStatus.IsRunning) {
            val newBoard = ticTacToeState.board.toMutableList().apply {
                set(
                    index, when (ticTacToeState.currentPlayer) {
                        is Player.x -> Player.x.symbol
                        is Player.o -> Player.o.symbol
                        Player.emp -> Player.emp.symbol
                    }
                )
            }
            val winnerInfo = checkWinner(newBoard)
            /**
             * Calculates the current game status based on the winner of the move.
             *
             * @param[winnerInfo] contains the winner symbol and the winning pattern
             * @return the current game status
             */
            val gameStatus = when (winnerInfo.gameStatus) {
                is GameStatus.XWins -> GameStatus.XWins
                is GameStatus.OWins -> GameStatus.OWins
                is GameStatus.Draw -> GameStatus.Draw
                else -> if (newBoard.none { it == " " }) GameStatus.Draw else GameStatus.IsRunning
            }

            _uiState.update { state ->
                state.copy(
                    board = newBoard,
                    currentPlayer = if (state.currentPlayer == Player.x) Player.o else Player.x,
                    gameStatus = gameStatus,
                    patternNumber = winnerInfo.patternNumber
                )

            }
        }

    }
}


    fun checkWinner(board: List<String?>): TTTState {
        val winPatterns = listOf(
            // Rows
            listOf(0, 1, 2),
            listOf(3, 4, 5),
            listOf(6, 7, 8),
            // Columns
            listOf(0, 3, 6),
            listOf(1, 4, 7),
            listOf(2, 5, 8),
            // Diagonals
            listOf(0, 4, 8),
            listOf(2, 4, 6)
        )
        
        for (pattern in winPatterns) {
            val (a, b, c) = pattern
            // If all three positions match the same symbol
            if (board[a] != " " && board[a] == board[b] && board[a] == board[c]) {
                val status = when (board[a]) {
                    Player.x.symbol -> GameStatus.XWins
                    Player.o.symbol -> GameStatus.OWins
                    else -> GameStatus.IsRunning
                }
                return TTTState(gameStatus = status, patternNumber = pattern).also { it ->
                    Log.d("it" , it.patternNumber.toString())
                }
            }
        }
        return TTTState()
    }


