package com.erendogan6.kekodproject1

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun gradientBackground(
    startColor: Color = Color(0xFF4CAF50),
    endColor: Color = Color(0xFF8BC34A),
    strokeColor: Color = MaterialTheme.colorScheme.primary,
    cornerRadius: Int = 65,
    outerBackgroundColor: Color = Color(0xFFE0F7FA),
    content: @Composable () -> Unit,
) {
    // Outer Box to set the background of the entire area
    Box(
        modifier =
            Modifier
                .fillMaxSize()
                .background(outerBackgroundColor),
    ) {
        // Inner Box for the gradient content
        Box(
            modifier =
                Modifier
                    .padding(top = 200.dp, bottom = 200.dp, start = 50.dp, end = 50.dp)
                    .background(
                        brush =
                            Brush.linearGradient(
                                colors = listOf(startColor, endColor),
                                start = Offset.Zero,
                                end = Offset.Infinite,
                            ),
                        shape = RoundedCornerShape(cornerRadius.dp),
                    ).border(
                        width = 1.dp,
                        color = strokeColor,
                        shape = RoundedCornerShape(cornerRadius.dp),
                    ),
        ) {
            content()
        }
    }
}
