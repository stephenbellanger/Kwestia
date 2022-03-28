package com.sbellanger.ui_kit.view

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sbellanger.ui_kit.R

@ExperimentalAnimationApi
@Composable
fun LoaderView(paddingTop: Dp) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = paddingTop)
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(100.dp)
                .fillMaxSize(),
            color = colorResource(R.color.white),
            strokeWidth = 5.dp
        )
    }
}
