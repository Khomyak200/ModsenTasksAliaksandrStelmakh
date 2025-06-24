package com.example.modsen_tasks_aliaksandrstelmakh.ui.loaders

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.size
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.Dp
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import com.example.modsen_tasks_aliaksandrstelmakh.R
import androidx.compose.runtime.getValue
import androidx.compose.material.Icon

@Composable
fun InfiniteLoadingIndicator(
    modifier: Modifier = Modifier,
    size: Dp = 88.dp,
    color: Color = MaterialTheme.colors.primary
) {
    val infiniteTransition = rememberInfiniteTransition()
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    Icon(
        painter = painterResource(id = R.drawable.ic_launcher_foreground),
        contentDescription = null,
        modifier = modifier.rotate(rotation).size(size),
        tint = color
    )
}