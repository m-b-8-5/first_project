package com.mb.myapplication.utils

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.mb.myapplication.R

sealed class Screen (val route: String, @StringRes val resourceId: Int, @DrawableRes val iconId: Int) {
    object Top : Screen("Top", R.string.top_screen, R.drawable.ic_bottom_home)
    object InputList : Screen("InputList", R.string.input_list_screen, R.drawable.ic_bottom_list)
}