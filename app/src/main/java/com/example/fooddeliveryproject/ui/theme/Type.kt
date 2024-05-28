package com.example.fooddeliveryproject.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.fooddeliveryproject.R

val fonts by lazy {
    FontFamily(
        fonts = listOf(
            Font(R.font.popins_regular, style = FontStyle.Normal, weight = FontWeight.Normal),
            Font(R.font.poppins_medium, style = FontStyle.Normal, weight = FontWeight.Medium),
            Font(R.font.poppins_semibold, style = FontStyle.Normal, weight = FontWeight.SemiBold)

        )
    )
}



val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)