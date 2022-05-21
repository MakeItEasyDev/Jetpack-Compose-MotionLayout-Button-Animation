package com.jetpack.motionlayoutbuttonrearrange

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.layoutId
import com.jetpack.motionlayoutbuttonrearrange.ui.theme.MotionLayoutButtonReArrangeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MotionLayoutButtonReArrangeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = {
                                    Text(
                                        text = "MotionLayout Button",
                                        modifier = Modifier.fillMaxWidth(),
                                        textAlign = TextAlign.Center
                                    )
                                }
                            )
                        }
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            MotionLayoutButtonReArrange()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MotionLayoutButtonReArrange() {
    var animateButton by remember { mutableStateOf(false) }
    val buttonAnimationProgress by animateFloatAsState(
        targetValue = if (animateButton) 1f else 0f,
        animationSpec = tween(1000)
    )

    MotionLayout(
        ConstraintSet(
            """ {
                button1: { 
                  width: "spread",
                  height: 60,
                  start: ['parent', 'start', 16],
                  end: ['parent', 'end', 16],
                  top: ['parent', 'top', 16]
                },
                button2: { 
                  width: "spread",
                  height: 60,
                  start: ['parent', 'start', 16],
                  end: ['parent', 'end', 16],
                  top: ['button1', 'bottom', 16]
                },
                button3: { 
                  width: "spread",
                  height: 60,
                  start: ['parent', 'start', 16],
                  end: ['parent', 'end', 16],
                  top: ['button2', 'bottom', 16]
                }
            } """
        ),
        ConstraintSet(
            """ {
                button1: { 
                  width: 100,
                  height: 60,
                  start: ['parent', 'start', 16],
                  end: ['button2', 'start', 16]
                },
                button2: { 
                  width: 100,
                  height: 60,
                  start: ['button1', 'end', 16],
                  end: ['button2', 'start', 16]
                },
                button3: { 
                  width: 100,
                  height: 60,
                  start: ['button2', 'end', 16],
                  end: ['parent', 'end', 16]
                }
            } """
        ),
        progress = buttonAnimationProgress,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Button(
            onClick = {
                animateButton = !animateButton
            },
            modifier = Modifier.layoutId("button1")
        ) {
            Text(text = "Button1")
        }
        Button(
            onClick = {
                animateButton = !animateButton
            },
            modifier = Modifier.layoutId("button2")
        ) {
            Text(text = "Button2")
        }
        Button(
            onClick = {
                animateButton = !animateButton
            },
            modifier = Modifier.layoutId("button3")
        ) {
            Text(text = "Button3")
        }
    }
}
























