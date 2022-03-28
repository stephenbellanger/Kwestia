package com.sbellanger.issue.presentation.view

import android.content.res.Configuration
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sbellanger.ui_kit.R

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Composable
fun IssueNumberIndicatorView(
    issueCount: Int
) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(colorResource(R.color.black))
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(com.sbellanger.issue.R.drawable.ic_circle_exclamation),
            contentDescription = null,
            modifier = Modifier
                .width(20.dp)
                .height(20.dp),
            colorFilter = ColorFilter.tint(colorResource(R.color.green))
        )

        Text(
            text = issueCount.toString(),
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp),
            color = colorResource(R.color.white),
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp
        )
    }
}

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Preview(showBackground = true)
@Composable
fun IssueNumberIndicatorViewDefaultPreview() {
    IssueNumberIndicatorView(10)
}

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun IssueNumberIndicatorViewDarkModePreview() {
    IssueNumberIndicatorView(10)
}
