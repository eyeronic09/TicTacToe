package com.example.tictactoe.HomeScreen.Component



import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.R

@Composable
fun BoardGrid(
    board: List<String>,
    onCellClicked: (index: Int) -> Unit ,
    indexToColor: List<Int>? = null,
) {
    val gridState = rememberLazyGridState()
    Box(
        contentAlignment = Alignment.Center,

        ) {

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            state = gridState,
            modifier = Modifier
                .fillMaxWidth(fraction = 0.9f)
                .aspectRatio(ratio = 1f)
                .background(Color(0xFF404F6C), shape = RoundedCornerShape(24.dp))
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(count = 9) { index ->
                CustomButton(
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
    fun CustomButton(
        onClick: () -> Unit,
        onEnable: () -> Boolean,
        indexToColor: List<Int>?,
        index: Int,
        content: @Composable () -> Unit
    ) {

        val colorIndex = indexToColor?.contains(index) ?: false
        Box(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(24.dp))
                .fillMaxSize()
                .aspectRatio(1f)
                .clickable(
                    enabled = onEnable(),
                    onClick = onClick
                )
                .background(
                    color = if (colorIndex) {
                        Color.Green
                    } else {
                        Color.LightGray
                    }
                )
                .border(1.dp, Color.DarkGray, shape = RectangleShape)
                .padding(4.dp)
        ) {
            content()
        }
    }

