package com.sbellanger.favorite.presentation.view

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sbellanger.favorite.domain.model.FavoriteEntity
import com.sbellanger.favorite.presentation.FavoriteViewAction
import com.sbellanger.ui_kit.R

@ExperimentalAnimationApi
@Composable
fun FavoriteView(
    favorite: FavoriteEntity,
    onEventHandler: (FavoriteViewAction) -> Unit
) {
    Column(
        modifier = Modifier
            .background(colorResource(R.color.black))
            .padding(bottom = 16.dp)
            .clickable {
                onEventHandler.invoke(
                    FavoriteViewAction.GoToIssue(favorite.repositoryName, favorite.openedIssues)
                )
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = favorite.repositoryName,
                modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp, end = 16.dp)
                    .weight(1f)
                    .alignByBaseline(),
                color = colorResource(R.color.white),
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )

            Image(
                painter = painterResource(R.drawable.ic_star),
                contentDescription = stringResource(R.string.accessibility_add_favorite),
                modifier = Modifier
                    .alignByBaseline()
                    .padding(top = 16.dp, end = 16.dp)
                    .width(16.dp)
                    .height(16.dp)
                    .clickable {
                        onEventHandler.invoke(
                            FavoriteViewAction.RepositoryRemoved(favorite.id)
                        )
                    },
            )
        }
        Text(
            text = favorite.description,
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp, end = 16.dp)
                .fillMaxWidth(),
            color = colorResource(R.color.white),
            maxLines = 3,
            fontSize = 12.sp,
            overflow = TextOverflow.Ellipsis
        )

        Divider(
            color = colorResource(R.color.grey),
            thickness = 1.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 20.dp, end = 16.dp)
        )

        Row {
            Image(
                painter = painterResource(com.sbellanger.favorite.R.drawable.ic_circle),
                contentDescription = null,
                modifier = Modifier
                    .alignByBaseline()
                    .padding(top = 16.dp, start = 16.dp)
                    .width(15.dp)
                    .height(15.dp),
                colorFilter = ColorFilter.tint(Color(favorite.color))
            )

            Text(
                text = favorite.language,
                modifier = Modifier
                    .alignByBaseline()
                    .padding(top = 16.dp, start = 4.dp),
                color = colorResource(R.color.white),
                fontSize = 12.sp
            )

            Image(
                painter = painterResource(R.drawable.ic_issue),
                contentDescription = null,
                modifier = Modifier
                    .alignByBaseline()
                    .padding(top = 16.dp, start = 16.dp)
                    .width(20.dp)
                    .height(20.dp),
                colorFilter = ColorFilter.tint(colorResource(R.color.white))
            )

            Text(
                text = favorite.openedIssues.toString(),
                modifier = Modifier
                    .alignByBaseline()
                    .padding(top = 16.dp, start = 2.dp, end = 16.dp),
                color = colorResource(R.color.white),
                fontSize = 12.sp
            )
        }
    }
}
