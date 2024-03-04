package com.example.myapplication.arunproject.presentation.view.compose

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.arun.myrecipeapplication.R

/**
 * RecipeAppBar is used to show the app bar with menu
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RecipeAppBar(
    onMenuItemClick: (Boolean) -> Unit,
    searchQuery: MutableState<String>,
    onSearchTextChange: (String) -> Unit,
    onSearchClicked: () -> Unit,
    onSearchClose: () -> Unit,
    isSearchVisible: Boolean
) {
    var showMenu by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val interactionSource by remember { mutableStateOf(MutableInteractionSource()) }
    val focusRequester = remember { FocusRequester() }

    TopAppBar(
        title = {
            if (isSearchVisible) {
                BasicTextField(
                    value = searchQuery.value,
                    onValueChange = {
                        searchQuery.value = it
                        onSearchTextChange(searchQuery.value)
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Search,
                        capitalization =
                        KeyboardCapitalization.Words,
                    ),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            onSearchTextChange(searchQuery.value)
                            keyboardController?.hide()
                        },
                    ),
                    cursorBrush = SolidColor(colorResource(id = R.color.white)),
                    textStyle = MaterialTheme.typography.body2.copy(color = colorResource(id = R.color.white)),
                    decorationBox = { innerTextField ->
                        TextFieldDefaults.TextFieldDecorationBox(
                            value = searchQuery.value,
                            innerTextField = innerTextField,
                            singleLine = true,
                            visualTransformation = VisualTransformation.None,
                            placeholder = {
                                Text(
                                    text = stringResource(id = R.string.search_hint),
                                    style = MaterialTheme.typography.body2,
                                    color = colorResource(id = R.color.white),
                                )
                            },
                            enabled = true,
                            interactionSource = interactionSource,
                            contentPadding = PaddingValues(0.dp),
                        )
                        innerTextField()
                    },
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen.padding_5))
                        .fillMaxWidth()
                        .focusRequester(focusRequester),
                )
                LaunchedEffect(Unit) {
                    focusRequester.requestFocus()
                }
            } else {
                Text(stringResource(id = R.string.app_name))
            }
        },

        backgroundColor = colorResource(id = R.color.blue_primary),
        contentColor = Color.White,
        elevation = dimensionResource(id = R.dimen.padding_4),
        actions = {
            if (isSearchVisible) {
                IconButton(onClick = onSearchClose) {
                    Icon(Icons.Filled.Close, contentDescription = "Close search")
                }
            } else {
                IconButton(onClick = onSearchClicked) {
                    Icon(Icons.Filled.Search, contentDescription = "Search")
                }
            }

            // Simple icon button with a dropdown menu
            IconButton(onClick = { showMenu = !showMenu }) {
                Icon(Icons.Filled.MoreVert, contentDescription = "More")
            }

            DropdownMenu(
                expanded = showMenu,
                onDismissRequest = { showMenu = false }
            ) {
                DropdownMenuItem(onClick = {
                    onMenuItemClick(false)
                    showMenu = false
                }) {
                    Text(stringResource(id = R.string.menu_vertical_list))
                }
                DropdownMenuItem(onClick = {
                    onMenuItemClick(true)
                    showMenu = false
                }) {
                    Text(stringResource(id = R.string.menu_grid_list))
                }
            }
        }

    )
}