package com.sudhirkhanger.wordle

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sudhirkhanger.wordle.ui.theme.*

/**
 * border - yes or no
 * background color - none, gray, yellow, green
 * letter or none
 *
 * empty
 * filled - without entered
 * filled - gray
 * filled - yellow
 * filled - green
 */

enum class BorderStyle() {
    UNFILLED,
    ENTERED,
    LOCKED
}

data class BlockState(val letter: String?, val borderStyle: Int, val background: Color)

@Composable
fun Block(
    letter: String,
    borderStroke: BorderStroke?,
    backgroundColor: Color,
    cardSize: Dp
) {
    Card(
        modifier = Modifier.size(cardSize),
        border = borderStroke,
        backgroundColor = backgroundColor
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = letter,
                color = Color.White,
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LetterGrid(letters: List<Char?>) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(count = 5),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(count = letters.size) { index ->
            val blockLetter = letters[index]?.toString() ?: ""
            val borderStroke = BorderStroke(1.dp, emptyBorder)
            val backgroundColor = blockBackground
            Block(letter = blockLetter, borderStroke, backgroundColor, 64.dp)
        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultWordlePreview() {
    WordleTheme {
        Surface(color = blockBackground) {
            val letters = List<Char?>(30) { null }
            Column {
                LetterGrid(letters = letters)

                var triggered by remember { mutableStateOf(false) }

                val size by animateDpAsState(if (triggered) 66.dp else 64.dp)

                val blockLetter = "M"
                val borderStroke = BorderStroke(1.dp, emptyBorder)
                val backgroundColor = blockBackground
                Block(letter = blockLetter, borderStroke, backgroundColor, size)
                Button(onClick = { triggered = true }) {}
            }
        }
    }
}
