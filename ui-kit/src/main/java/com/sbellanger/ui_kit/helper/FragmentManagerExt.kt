package com.sbellanger.ui_kit.helper

import androidx.fragment.app.FragmentManager

fun FragmentManager.isFirstLevel(): Boolean {
    return fragments.size == 2
}
