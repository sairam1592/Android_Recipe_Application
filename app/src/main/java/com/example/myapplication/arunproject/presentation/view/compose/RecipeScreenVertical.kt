package com.example.myapplication.arunproject.presentation.view.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.arun.myapplication.R
import com.example.myapplication.arunproject.common.AppConstants
import com.example.myapplication.arunproject.common.getCurrentDate
import com.example.myapplication.arunproject.data.model.Recipe
import com.example.myapplication.arunproject.presentation.view.state.RecipeViewState
import com.example.myapplication.arunproject.presentation.viewmodel.RecipeViewModel

@Composable
fun RecipeScreenVertical(recipeViewModel: RecipeViewModel = viewModel()) {
    val recipesState by recipeViewModel.recipeState.collectAsState()

    Scaffold(content = {
        Box(modifier = Modifier.padding(it)) {
            when (recipesState) {
                is RecipeViewState.Loading -> {
                    LoadingScreen()
                }

                is RecipeViewState.Success -> {
                    val recipes = (recipesState as RecipeViewState.Success).recipes
                    RecipeList(recipes)
                }

                is RecipeViewState.Error -> {
                    val errorMessage = (recipesState as RecipeViewState.Error).message
                    ErrorScreen() // Show error screen

                    Column {
                        Row(
                            modifier = Modifier.weight(1f, fill = true),
                            verticalAlignment = Alignment.Bottom
                        ) {
                            ShowErrorSnackBarWithoutAction()
                        }
                    }
                }
            }
        }
    })
}

/**
 * Show loading screen
 */
@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.padding_20)),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(color = colorResource(id = R.color.blue_primary))
    }
}

/**
 * Show error screen
 */
@Composable
fun ErrorScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.padding_16)),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Text(
                AppConstants.ERROR_MESSAGE,
                color = Color.Red,
                fontSize = dimensionResource(id = R.dimen.text_size_18).value.sp
            )
        }
    }
}

/**
 * Show recipe list
 */
@Composable
fun RecipeList(recipes: List<Recipe>) {

    LazyColumn {

        item {
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_10)))
        }

        item {
            Text(
                text = getCurrentDate(),
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                    fontSize = dimensionResource(id = R.dimen.text_size_large).value.sp
                ),
                modifier = Modifier.padding(
                    start = dimensionResource(id = R.dimen.padding_20),
                    end = dimensionResource(id = R.dimen.padding_20)
                ),
                color = colorResource(id = R.color.blue_primary)
            )
        }

        items(recipes.size) { index ->
            if (index > 0) {
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_5)))
            }
            RecipeItem(recipe = recipes[index])

            if (index == recipes.size - 1) {
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_10)))
            }
        }
    }
}