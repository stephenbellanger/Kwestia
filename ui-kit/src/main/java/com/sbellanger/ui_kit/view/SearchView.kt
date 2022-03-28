package com.sbellanger.ui_kit.view

import android.content.res.Configuration
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sbellanger.ui_kit.R

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Composable
fun SearchView(
    text: String,
    onTextChange: (String) -> Unit,
    onClear: () -> Unit
) {
    val scroll = rememberScrollState(0)
    val keyboardController = LocalSoftwareKeyboardController.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(colorResource(R.color.black))
            .padding(start = 16.dp, end = 16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.ic_search),
            contentDescription = stringResource(R.string.accessibility_search_icon),
            modifier = Modifier
                .width(20.dp)
                .height(20.dp),
            colorFilter = ColorFilter.tint(colorResource(R.color.grey))
        )

        TextField(
            value = text,
            placeholder = {
                Text(
                    text = stringResource(R.string.search_hint),
                    color = colorResource(R.color.grey)
                )
            },
            onValueChange = onTextChange,
            maxLines = 1,
            textStyle = TextStyle(
                color = colorResource(R.color.white),
                fontSize = 14.sp
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search,
                keyboardType = KeyboardType.Text
            ),
            keyboardActions = KeyboardActions(
                onSearch = { keyboardController?.hide() }
            ),
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp, end = 16.dp)
                .horizontalScroll(scroll),
            colors = TextFieldDefaults.textFieldColors(
                textColor = colorResource(R.color.white),
                disabledTextColor = Color.Transparent,
                backgroundColor = colorResource(R.color.black),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                cursorColor = colorResource(R.color.white)
            )
        )

        Image(
            painter = painterResource(R.drawable.ic_cross),
            contentDescription = stringResource(R.string.accessibility_cross_icon),
            modifier = Modifier
                .width(10.dp)
                .height(10.dp)
                .clickable { onClear.invoke() },
            colorFilter = ColorFilter.tint(colorResource(R.color.grey))
        )
    }
}

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Preview(showBackground = true)
@Composable
fun SearchViewDefaultPreview() {
    SearchView("test", {}) {}
}

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun SearchViewDarkModePreview() {
    SearchView("test", {}) {}
}
