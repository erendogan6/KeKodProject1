package com.erendogan6.kekodproject1.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.erendogan6.kekodproject1.R

@Composable
fun switchFragmentContent(
    switchName: String,
    iconRes: Int,
) {
    val context = LocalContext.current
    val welcomeText = context.getString(R.string.welcome_fragment, switchName)

    // Wrapper Column for entire content
    Column(
        modifier = Modifier.fillMaxSize().background(Color(0xFFE0F7FA)),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // Green Box content with gradient background
        Box(
            modifier = Modifier.weight(0.8f),
        ) {
            gradientBackground {
                Column(
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    // Icon for the fragment
                    Icon(
                        painter = painterResource(id = iconRes),
                        contentDescription = "Icon for $switchName Fragment",
                        modifier =
                            Modifier
                                .size(110.dp)
                                .padding(bottom = 12.dp),
                        tint = Color(0xFFFFEB3B),
                    )

                    // Text for Detail Screen
                    Text(
                        text = welcomeText,
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
                        modifier = Modifier.padding(6.dp),
                    )

                    // Decorative line
                    HorizontalDivider(
                        modifier =
                            Modifier
                                .width(150.dp)
                                .padding(top = 12.dp),
                        thickness = 6.dp,
                        color = Color(0xFF03A9F4),
                    )
                }
            }
        }

        // Lottie animation directly below the green box
        lottieLoadingScreen(
            modifier =
                Modifier.weight(0.2f),
        )
    }
}
