package com.example.tictactoe.HomeScreen



import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.colorspace.ColorSpace
import androidx.compose.ui.res.booleanResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tictactoe.HomeScreen.domain.Player

@Composable
fun BoardGrid(
    board: List<String>,
    onCellClicked: (index: Int) -> Unit ,
    indexToColor: List<Int>? = null,
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
            items(count = 9) { index ->
                customButton(
                    onClick = { onCellClicked(index) },
                    onEnable = {
                        val value = board.getOrNull(index)
                        value != "X" && value != "O"
                    },
                    indexToColor = indexToColor,
                    index = index,
                    content = {
                        Text(
                            text = board.getOrElse(index) { " " },
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
    onEnable: () -> Boolean,
    indexToColor: List<Int>? ,
    index: Int,
    content: @Composable () -> Unit
) {

    val colorIndex = indexToColor?.contains(index) ?: false
    Box(
        modifier = Modifier
            .fillMaxSize()
            .aspectRatio(1f)
            .clickable(
                enabled = onEnable(),
                onClick = onClick
            )
            .background(
                color = if (colorIndex) {
                    Color.Green
                }
                else {
                    Color.LightGray
                }
            )
            .border(1.dp, Color.DarkGray, shape = RectangleShape)
            .padding(4.dp)
    ) {
        content()
    }
}

//@Preview(showBackground = true, showSystemUi = true)
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
        onCellClicked = {} ,
        indexToColor = listOf(1, 4, 7)
    )
}
