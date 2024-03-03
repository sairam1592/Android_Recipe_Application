package com.example.myapplication.arunproject.presentation.view.compose

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHost
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.arun.myapplication.R
import com.example.myapplication.arunproject.data.model.Recipe
import com.example.myapplication.arunproject.presentation.view.compose.recipelist.RecipeList
import com.example.myapplication.arunproject.presentation.view.state.RecipeViewState
import com.example.myapplication.arunproject.presentation.viewmodel.RecipeViewModel
import kotlinx.coroutines.launch

/**
 * RecipeScreenVertical is used to show the recipe list in vertical list
 */
@ExperimentalMaterialApi
@Composable
fun RecipeScreenVertical(
    recipeViewModel: RecipeViewModel = viewModel(), isShowAdaptiveGrid: Boolean
) {
    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val recipesState by recipeViewModel.recipeState.collectAsStateWithLifecycle()

    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val searchQuery = rememberSaveable { mutableStateOf("") }
    var isSearchVisible by remember { mutableStateOf(false) }

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetShape = RoundedCornerShape(
            topStart = dimensionResource(id = R.dimen.padding_16),
            topEnd = dimensionResource(id = R.dimen.padding_16)
        ),
        sheetBackgroundColor = colorResource(id = R.color.blue_light),
        sheetContent = {
            BottomSheetContent()
        }
    ) {
        Scaffold(scaffoldState = scaffoldState, topBar = {
            RecipeAppBar(
                onMenuItemClick = { isShowGridList ->
                    recipeViewModel.showGridList(isShowGridList)
                },
                searchQuery = searchQuery,
                onSearchTextChange = {
                    searchQuery.value = it
                    recipeViewModel.searchRecipe(it)
                },
                isSearchVisible = isSearchVisible,
                onSearchClicked = { isSearchVisible = true },
                onSearchClose = { isSearchVisible = false })
        }, snackbarHost = {
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
                        val isShowGrid = (recipesState as RecipeViewState.Success).isShowGrid
                        val searchQuery = (recipesState as RecipeViewState.Success).searchQuery

                        if (searchQuery.isNotEmpty()) {
                            val filteredRecipes =
                                recipes.filter { it.name.contains(searchQuery, ignoreCase = true) }
                            RecipeList(filteredRecipes,
                                isShowGrid = isShowGrid,
                                isShowAdaptiveGrid,
                                onRecipeClick = { recipeId ->
                                    recipeViewModel.onRecipeSelected(
                                        recipeId
                                    )
                                })
                        } else {
                            RecipeList(recipes,
                                isShowGrid = isShowGrid,
                                isShowAdaptiveGrid,
                                onRecipeClick = { recipeId ->
                                    recipeViewModel.onRecipeSelected(
                                        recipeId
                                    )
                                })
                        }
                    }

                    is RecipeViewState.Error -> {
                        val errorMessage = (recipesState as RecipeViewState.Error).message
                        ErrorScreen(onRetry = {
                            recipeViewModel.loadRecipes()
                        })

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
        }, floatingActionButton = {
            FloatingActionButton(
                backgroundColor = colorResource(id = R.color.blue_primary),
                onClick = {
                    coroutineScope.launch {
                        if (sheetState.isVisible) {
                            sheetState.hide()
                        } else {
                            sheetState.show()
                        }
                    }
                }) {
                Icon(
                    tint = colorResource(id = R.color.white),
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
            }
        })
    }
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