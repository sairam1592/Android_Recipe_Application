package com.example.myapplication.arunproject.presentation.view.compose

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHost
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.arunproject.data.model.Recipe
import com.example.myapplication.arunproject.presentation.view.compose.recipelist.RecipeList
import com.example.myapplication.arunproject.presentation.view.state.RecipeViewState
import com.example.myapplication.arunproject.presentation.viewmodel.RecipeViewModel

@Composable
fun RecipeScreenVertical(
    recipeViewModel: RecipeViewModel = viewModel(), isShowGrid: Boolean, isShowAdaptiveGrid: Boolean
) {
    val recipesState by recipeViewModel.recipeState.collectAsStateWithLifecycle()
    val scaffoldState = rememberScaffoldState()

    Scaffold(scaffoldState = scaffoldState, snackbarHost = {
        SnackbarHost(it) { data ->
            ShowOnClickSnackBarWithoutAction(data.message)
        }
    }, content = {
        Box(modifier = Modifier.padding(it)) {
            when (recipesState) {
                is RecipeViewState.Loading -> {
                    LoadingScreen()
                }

                is RecipeViewState.Success -> {
                    val recipes = (recipesState as RecipeViewState.Success).recipes
                    RecipeList(recipes,
                        isShowGrid,
                        isShowAdaptiveGrid,
                        onRecipeClick = { recipeId -> recipeViewModel.onRecipeSelected(recipeId) })
                }

                is RecipeViewState.Error -> {
                    val errorMessage = (recipesState as RecipeViewState.Error).message
                    ErrorScreen()

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

        if (recipesState is RecipeViewState.Success) {
            val selectedRecipeId = (recipesState as RecipeViewState.Success).selectedRecipeId
            val selectedRecipeName =
                (recipesState as RecipeViewState.Success).recipes.firstOrNull { it.id == selectedRecipeId }?.name

            selectedRecipeName?.let { name ->

                LaunchedEffect(name) {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = name,
                        duration = SnackbarDuration.Short
                    )

                    recipeViewModel.clearSelectedRecipeId()
                }
            }
        }
    })
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun PreviewRecipeList() {
    val dummyRecipes = List(10) { index ->
        Recipe(
            calories = "200 kcal",
            carbos = "20g",
            description = "A delicious fish recipe $index",
            difficulty = 1,
            fats = "5g",
            headline = "Tasty Fish $index",
            id = index.toString(),
            image = "",
            name = "Crispy Fish Goujons $index",
            proteins = "10g",
            thumb = "",
            time = "30 min"
        )
    }
    RecipeList(recipes = dummyRecipes,
        isShowGrid = false,
        isShowGridAdaptive = false,
        onRecipeClick = {})
}