package com.example.tictactoe.HomeScreen



import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun BoardGrid(
    board: List<String>,
    onCellClicked: (index: Int) -> Unit
) {
    val gridState = rememberLazyGridState()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            state = gridState,
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .aspectRatio(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(9) { index ->
                customButton(
                    onClick = { onCellClicked(index) },
                    content = {
                        Text(
                            text = board.getOrElse(index) { "_" },
                            modifier = Modifier.fillMaxSize(),
                            textAlign = TextAlign.Center
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun customButton(
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .aspectRatio(1f)
            .clickable(onClick = onClick)
            .background(Color.LightGray.copy(alpha = 0.3f))
            .border(1.dp, Color.DarkGray, shape = RectangleShape)
            .padding(4.dp),
        contentAlignment = Alignment.Center,
    ) {
        content()
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun BoardPreview() {
    val sampleBoard = List(9) { index ->
        when (index % 3) {
            0 -> "X"
            1 -> "O"
            else -> "_"
        }
    }

    BoardGrid(
        board = sampleBoard,
        onCellClicked = {}
    )
}