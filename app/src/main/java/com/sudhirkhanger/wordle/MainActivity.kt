package com.sudhirkhanger.wordle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sudhirkhanger.wordle.ui.theme.WordleTheme
import com.sudhirkhanger.wordle.ui.theme.blockBackground
import com.sudhirkhanger.wordle.ui.theme.emptyBorder
import com.sudhirkhanger.wordle.ui.theme.green

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WordleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colors.background
                color = blockBackground
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        var triggered by remember { mutableStateOf(false) }
                        var triggered2 by remember { mutableStateOf(false) }

                        val size by animateDpAsState(
                            targetValue = if (triggered) 68.dp else 64.dp,
                            animationSpec = tween(50, easing = LinearEasing),
                            finishedListener = { triggered2 = false }
                        )


                        val backColor by animateColorAsState(
                            targetValue = if (triggered) green else blockBackground,
                            animationSpec = tween(100, easing = LinearEasing),
                            finishedListener = {  }
                        )

                        Button(onClick = { triggered = true }) {
                            Text(text = "Animate")
                        }

                        val blockLetter = "M"
                        val borderStroke = BorderStroke(1.dp, emptyBorder)
                        val backgroundColor = blockBackground
                        Block(letter = blockLetter, borderStroke, backColor, size)

                    }
                }
            }
        }
    }
}
