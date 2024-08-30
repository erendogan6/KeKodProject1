package com.erendogan6.kekodproject1.model

import androidx.annotation.DrawableRes

data class SwitchModel(
    val id: Int,
    val name: String,
    @DrawableRes val iconRes: Int,
    var isChecked: Boolean = false,
)
