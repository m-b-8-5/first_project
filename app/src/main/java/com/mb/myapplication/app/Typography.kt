package com.mb.myapplication.app

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.mb.myapplication.R

val rubik = FontFamily(
    Font(R.font.rubik_variablefont_wght)
)
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = rubik,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        color = BaseAppColor,
    )
)