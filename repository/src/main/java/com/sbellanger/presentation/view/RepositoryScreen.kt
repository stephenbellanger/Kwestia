package com.sbellanger.presentation.view

import android.content.res.Configuration
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sbellanger.presentation.RepositoryViewAction
import com.sbellanger.presentation.RepositoryViewState
import com.sbellanger.ui_kit.R
import com.sbellanger.ui_kit.view.ErrorView
import com.sbellanger.ui_kit.view.LoaderView
import com.sbellanger.ui_kit.view.NoResultView
import com.sbellanger.ui_kit.view.SearchView

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Composable
fun RepositoryScreen(
    viewState: RepositoryViewState,
    text: String,
    onTextChange: (String) -> Unit,
    onEventHandler: (RepositoryViewAction) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.midnight_blue))
            .padding(start = 16.dp, end = 16.dp)
    ) {
        Row {
            Image(
                painter = painterResource(R.drawable.ic_app),
                contentDescription = null,
                modifier = Modifier
                    .alignByBaseline()
                    .padding(top = 32.dp)
                    .width(50.dp)
                    .height(50.dp)
            )

            Text(
                text = stringResource(R.string.app_name),
                modifier = Modifier
                    .alignByBaseline()
                    .weight(1f)
                    .padding(top = 32.dp, bottom = 32.dp),
                color = colorResource(R.color.white),
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp
            )
        }
        SearchView(
            text = text,
            onTextChange = onTextChange,
            onClear = { onEventHandler.invoke(RepositoryViewAction.ClearSearch) })

        when (viewState) {
            RepositoryViewState.Error -> ErrorScreen()
            is RepositoryViewState.Loaded -> LoadedScreen(viewState, onEventHandler)
            RepositoryViewState.Loading -> LoadingScreen()
            RepositoryViewState.NoResult -> NoResultScreen()
            RepositoryViewState.Init -> {
                // Do nothing
            }
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun LoadedScreen(
    viewState: RepositoryViewState.Loaded,
    onEventHandler: (RepositoryViewAction) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        items(viewState.repositories) { repository ->
            RepositoryView(repository, onEventHandler)
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
fun RepositoryScreenDefaultPreview() {
    RepositoryScreen(RepositoryViewState.Loading, "test", {}) {}
}

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun RepositoryScreenDarkModePreview() {
    RepositoryScreen(RepositoryViewState.Loading, "test", {}) {}
}

