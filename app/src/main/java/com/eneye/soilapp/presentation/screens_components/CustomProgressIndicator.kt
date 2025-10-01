package com.eneye.soilapp.presentation.screens_components
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomCircularProgressBar(
    percentage: Float?,
    number: Int,
    fontSize: TextUnit = 28.sp,
    radius: Dp = 50.dp,
    color: Color = Color.Green,
    strokeWidth: Dp = 8.dp,
    animDuration: Int = 1000,
    animDelay: Int = 0,
    symbol: String = "",
){
    var animationPlayed by remember {
        mutableStateOf(false)
    }

    val currPercentage  = (if(animationPlayed) percentage else 0f)?.let {
        animateFloatAsState(
            targetValue = it,
            animationSpec = tween(
                durationMillis = animDuration,
                delayMillis = animDelay
            )
        )
    }

    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(radius * 2f)
    ){
        androidx.compose.foundation.Canvas(
            modifier = Modifier.size(radius * 2f)

        ) {
            if (currPercentage != null) {
                drawArc(
                    color = color,
                    startAngle = -90f,
                    sweepAngle = 360 * currPercentage.value,
                    useCenter = false,
                    style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
                )
            }

        }

        if (currPercentage != null) {
            Text(
                text = (percentage?.times(100))?.toInt().toString() + "%",
                color = Color.Black,
                fontSize =  fontSize,
                fontWeight = FontWeight.Bold
            )
        }


    }

}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun CircPrev(){
    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        CustomCircularProgressBar(
            percentage = 0.8f,
            number = 100
        )
    }
}
