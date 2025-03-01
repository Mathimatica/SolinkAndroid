package com.example.solink.ui.view

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SLButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true, // Added to handle disabled state
    tint: Color = Color.White // Default tint matches contentColor from Solink scheme
) {
    val isPressed by remember { mutableStateOf(false) }

    IconButton(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .size(48.dp) // Larger touch target
            .clip(CircleShape) // Circular shape
            .background(
                color = if (enabled) Color(0xFF0288D1) else Color(0xFF01579B), // Solink blue, disabled darker
                shape = CircleShape
            )
    ) {
        AnimatedContent(
            targetState = isPressed,
            transitionSpec = {
                fadeIn() togetherWith fadeOut()
            }
        ) { pressed ->
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = if (enabled) {
                    if (pressed) tint.copy(alpha = 0.7f) else tint
                } else {
                    Color(0xFFB0BEC5) // Disabled content color from Solink scheme
                },
                modifier = Modifier
                    .size(24.dp)
                    .animateContentSize() // Smooth size animation
            )
        }
    }
}