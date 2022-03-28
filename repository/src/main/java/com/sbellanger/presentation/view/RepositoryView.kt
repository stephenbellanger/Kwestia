package com.sbellanger.presentation.view

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sbellanger.domain.model.RepositoryEntity
import com.sbellanger.presentation.RepositoryViewAction
import com.sbellanger.ui_kit.R

@ExperimentalAnimationApi
@Composable
fun RepositoryView(
    repository: RepositoryEntity,
    onEventHandler: (RepositoryViewAction) -> Unit
) {
    var isFavorite = repository.isFavorite

    Column(
        modifier = Modifier
            .background(colorResource(R.color.black))
            .padding(bottom = 16.dp)
            .clickable {
                onEventHandler.invoke(
                    RepositoryViewAction.GoToIssue(
                        repositoryName = repository.repositoryName,
                        issueCount = repository.openIssuesCount
                    )
                )
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = repository.name,
                modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp, end = 16.dp)
                    .weight(1f)
                    .alignByBaseline(),
                color = colorResource(R.color.white),
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )

            Image(
                painter = if (isFavorite) {
                    painterResource(R.drawable.ic_star)
                } else {
                    painterResource(R.drawable.ic_empty_star)
                },
                contentDescription = stringResource(R.string.accessibility_add_favorite),
                modifier = Modifier
                    .alignByBaseline()
                    .padding(top = 16.dp, end = 16.dp)
                    .width(16.dp)
                    .height(16.dp)
                    .clickable {
                        if (isFavorite) {
                            isFavorite = false
                            onEventHandler.invoke(RepositoryViewAction.RepositoryRemoved(repository))
                        } else {
                            isFavorite = true
                            onEventHandler.invoke(RepositoryViewAction.RepositoryAdded(repository))
                        }
                    },
            )
        }
        Text(
            text = repository.description,
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp, end = 16.dp)
                .fillMaxWidth(),
            color = colorResource(R.color.white),
            maxLines = 3,
            fontSize = 12.sp,
            overflow = TextOverflow.Ellipsis
        )

        Row {
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
                text = repository.openIssuesCount.toString(),
                modifier = Modifier
                    .alignByBaseline()
                    .padding(top = 16.dp, start = 2.dp, end = 16.dp),
                color = colorResource(R.color.white),
                fontSize = 12.sp
            )
        }
    }
}
