package com.sbellanger.ui_kit.view

import android.content.res.Configuration
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sbellanger.ui_kit.R
import com.sbellanger.ui_kit.view.ErrorView

@ExperimentalAnimationApi
@Composable
fun NoResultView(paddingTop: Dp) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = paddingTop),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            painter = painterResource(R.drawable.ic_no_result),
            contentDescription = null,
            modifier = Modifier
                .width(100.dp)
                .height(100.dp),
            colorFilter = ColorFilter.tint(colorResource(R.color.white))
        )

        Text(
            text = stringResource(id = R.string.no_result),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp, end = 16.dp)
                .fillMaxWidth(),
            color = colorResource(R.color.white)
        )

    }
}

@ExperimentalAnimationApi
@Preview(showBackground = true)
@Composable
fun NoResultViewDefaultPreview() {
    ErrorView(paddingTop = 32.dp)
}

@ExperimentalAnimationApi
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun NoResultViewDarkModePreview() {
    ErrorView(paddingTop = 32.dp)
}

