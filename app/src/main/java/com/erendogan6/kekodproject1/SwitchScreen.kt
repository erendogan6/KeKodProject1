package com.erendogan6.kekodproject1

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun switchFragmentContent(
    switchName: String,
    iconRes: Int,
) {
    gradientBackground {
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            // Icon for the fragment
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = "Icon for $switchName Fragment",
                modifier =
                    Modifier
                        .size(120.dp)
                        .padding(bottom = 16.dp),
                tint = Color(0xFFFFEB3B),
            )

            // Text for Detail Screen
            Text(
                text = "Welcome To $switchName Fragment",
                style =
                    MaterialTheme.typography.bodyLarge.copy(
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                        color = Color(0xFF333333),
                        shadow =
                            Shadow(
                                color = Color(0x55000000),
                                offset = Offset(2f, 2f),
                                blurRadius = 4f,
                            ),
                        textAlign = TextAlign.Center,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                    ),
                modifier = Modifier.padding(8.dp),
            )

            // Decorative line
            HorizontalDivider(
                modifier =
                    Modifier
                        .width(150.dp)
                        .padding(top = 16.dp),
                thickness = 6.dp,
                color = Color(0xFF03A9F4),
            )
        }
    }
}
