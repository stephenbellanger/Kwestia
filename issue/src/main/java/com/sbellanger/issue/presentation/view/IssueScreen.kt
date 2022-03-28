package com.sbellanger.issue.presentation.view

import android.content.res.Configuration
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sbellanger.issue.presentation.IssueViewAction
import com.sbellanger.issue.presentation.IssueViewState
import com.sbellanger.ui_kit.R
import com.sbellanger.ui_kit.view.ErrorView
import com.sbellanger.ui_kit.view.LoaderView
import com.sbellanger.ui_kit.view.NoResultView
import com.sbellanger.ui_kit.view.SearchView

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Composable
fun IssueScreen(
    viewState: IssueViewState,
    repositoryName: String,
    issueCount: Int,
    text: String,
    onTextChange: (String) -> Unit,
    onEventHandler: (IssueViewAction) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.midnight_blue))
            .padding(start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Image(
            painter = painterResource(com.sbellanger.issue.R.drawable.ic_back_arrow),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 16.dp)
                .width(30.dp)
                .height(30.dp)
                .clickable {
                    onEventHandler.invoke(IssueViewAction.GoBack)
                },
            colorFilter = ColorFilter.tint(colorResource(R.color.white))
        )

        Text(
            text = repositoryName,
            modifier = Modifier
                .fillMaxWidth(),
            color = colorResource(R.color.white),
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp
        )

        IssueNumberIndicatorView(
            issueCount = issueCount
        )

        SearchView(
            text = text,
            onTextChange = onTextChange,
            onClear = { onEventHandler.invoke(IssueViewAction.ClearSearch) })

        when (viewState) {
            IssueViewState.Error -> ErrorScreen()
            is IssueViewState.Loaded -> LoadedScreen(viewState)
            IssueViewState.Loading -> LoadingScreen()
            IssueViewState.NoIssue -> NoResultScreen()
            IssueViewState.Init -> {
                // Do nothing
            }
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun LoadedScreen(viewState: IssueViewState.Loaded) {
    LazyColumn(
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        items(viewState.issues) { issue ->
            IssueView(issue)
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun ErrorScreen() {
    ErrorView(paddingTop = 32.dp)
}

@ExperimentalAnimationApi
@Composable
fun NoResultScreen() {
    NoResultView(
        paddingTop = 32.dp
    )
}

@ExperimentalAnimationApi
@Composable
fun LoadingScreen() {
    LoaderView(
        paddingTop = 32.dp
    )
}

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Preview(showBackground = true)
@Composable
fun IssueScreenDefaultPreview() {
    IssueScreen(IssueViewState.Loading, "repository", 10, "test", {}) {}
}

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun IssueScreenDarkModePreview() {
    IssueScreen(IssueViewState.Loading, "repository", 10, "test", {}) {}
}
