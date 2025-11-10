package com.example.tictactoe.HomeScreen.ui.component

import android.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tictactoe.HomeScreen.domain.TicTacToeState

@Composable
fun GameBoard(
    items : List<List<String>> ,
    onCellClick : (row :Int , col : Int) -> Unit ,
    modifier: Modifier
    ) {
    val flatBoardList: List<String> = items.flatten()

    LazyVerticalGrid(
        columns = GridCells.Fixed(3), // Fixes the grid to exactly 3 columns
        modifier = Modifier
            .aspectRatio(1f) // Keep the grid square
            .padding(16.dp)

        ,


        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        userScrollEnabled = false
    ) {
        itemsIndexed(flatBoardList) { index , value ->
            val row = index / 3 ; val col = index % 3
            BoardCell(
                value = value ,
                onClick = {
                    onCellClick (row , col)

                }

            )

        }
    }


}


@Composable
fun BoardCell(
    value: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(4.dp)
            .clickable(onClick = onClick)
            .background(androidx.compose.ui.graphics.Color.DarkGray)
            ,
        contentAlignment = Alignment.Center
    ) {
        Text(text = value)
    }
}

@Composable
@Preview(showBackground = true)
fun Pre() {
  //  BoardCell("@" , onClick = {} )
    val sampleBoard = listOf(
        listOf("X", "O", ""),
        listOf("", "X", ""),
        listOf("O", "", "X")
    )

    GameBoard(
        items = sampleBoard ,
        onCellClick = { row, col ->

        },
        modifier = Modifier

    )
}