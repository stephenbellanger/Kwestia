package com.sbellanger.issue.presentation.view

import android.content.res.Configuration
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sbellanger.issue.domain.model.IssueEntity
import com.sbellanger.ui_kit.R

@ExperimentalAnimationApi
@Composable
fun IssueView(issue: IssueEntity) {
    Column(
        modifier = Modifier
            .background(colorResource(R.color.black))
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(com.sbellanger.issue.R.drawable.ic_circle_exclamation),
                contentDescription = null,
                modifier = Modifier
                    .width(10.dp)
                    .height(10.dp),
                colorFilter = ColorFilter.tint(colorResource(R.color.green))
            )

            Text(
                text = issue.name,
                modifier = Modifier
                    .padding(start = 4.dp, end = 16.dp)
                    .weight(1f),
                color = colorResource(R.color.grey),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 14.sp
            )

            Text(
                text = issue.number,
                modifier = Modifier
                    .padding(start = 8.dp),
                color = colorResource(R.color.grey),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 14.sp
            )
        }

        Text(
            text = issue.openedBy,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            color = colorResource(R.color.grey),
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp
        )
    }
}

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Preview(showBackground = true)
@Composable
fun IssueViewDefaultPreview() {
    IssueView(
        IssueEntity(
            name = "name",
            number = "#4",
            description = "description",
            openedBy = "Opened by toto"
        )
    )
}

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun IssueViewDarkModePreview() {
    IssueView(
        IssueEntity(
            name = "name",
            number = "#4",
            description = "description",
            openedBy = "Opened by toto"
        )
    )
}
