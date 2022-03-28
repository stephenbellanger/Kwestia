package com.sbellanger.favorite.presentation.view

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sbellanger.favorite.presentation.FavoriteViewAction
import com.sbellanger.favorite.presentation.FavoriteViewState
import com.sbellanger.ui_kit.R
import com.sbellanger.ui_kit.view.ErrorView
import com.sbellanger.ui_kit.view.LoaderView
import com.sbellanger.ui_kit.view.NoResultView

@ExperimentalAnimationApi
@Composable
fun FavoriteScreen(
    viewState: FavoriteViewState,
    onEventHandler: (FavoriteViewAction) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.midnight_blue))
    ) {
        Text(
            text = stringResource(R.string.favorite_title),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 32.dp),
            color = colorResource(R.color.white),
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp
        )
        when (viewState) {
            FavoriteViewState.Error -> ErrorScreen()
            is FavoriteViewState.Loaded -> LoadedScreen(viewState, onEventHandler)
            FavoriteViewState.Loading -> LoadingScreen()
            FavoriteViewState.NoResult -> NoResultScreen()
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun LoadedScreen(
    viewState: FavoriteViewState.Loaded,
    onEventHandler: (FavoriteViewAction) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .padding(top = 32.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        items(viewState.favorites) { favorite ->
            FavoriteView(favorite, onEventHandler)
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

@ExperimentalAnimationApi
@Preview(showBackground = true)
@Composable
fun FavoritesScreenDefaultPreview() {
    FavoriteScreen(FavoriteViewState.Loading) {}
}

@ExperimentalAnimationApi
@Preview(
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
fun FavoritesScreenDarkModePreview() {
    FavoriteScreen(FavoriteViewState.Loading) {}
}
