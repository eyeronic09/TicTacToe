package com.example.tictactoe.HomeScreen


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tictactoe.HomeScreen.domain.Player

@Composable
fun BoardGrid(board: List<String>, onCellClicked: (rowIndex: Int, colIndex: Int) -> Unit) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        repeat(3) { row -> 
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ){
                repeat(3){ col ->
                    val index = row * 3 + col
                    Button(
                        onClick = {}
                    ) {
                       Text(board.getOrElse(index) {""})
                    }
                }
            }
            
        }
    }
}
@Preview(showBackground = true)
@Composable
private fun BoardGridPreview() {
    val board = listOf(
        Player.x.symbol, Player.o.symbol, Player.x.symbol,
        Player.emp.symbol, Player.x.symbol, Player.emp.symbol,
        Player.emp.symbol, Player.emp.symbol, Player.emp.symbol
    )
    BoardGrid(
        board = board,
        onCellClicked = { rowIndex, colIndex ->
            println("Cell clicked at row: $rowIndex, column: $colIndex")
        }
    )
}