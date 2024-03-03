package com.example.myapplication.arunproject.presentation.view.compose

import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.arun.myapplication.R

/**
 * RecipeAppBar is used to show the app bar with menu
 */
@Composable
fun RecipeAppBar(onMenuItemClick: (Boolean) -> Unit) {
    var showMenu by remember { mutableStateOf(false) }

    TopAppBar(
        title = { Text(stringResource(id = R.string.app_name)) },
        backgroundColor = colorResource(id = R.color.blue_primary),
        contentColor = Color.White,
        elevation = dimensionResource(id = R.dimen.padding_4),
        actions = {
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