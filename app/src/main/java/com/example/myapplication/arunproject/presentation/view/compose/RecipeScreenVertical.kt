package com.example.myapplication.arunproject.presentation.view.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
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
                    ErrorScreen(errorMessage)
                }
            }
        }
    }, snackbarHost = {
        SnackbarHost(
            hostState = remember { SnackbarHostState() }, modifier = Modifier.padding(16.dp)
        )
    })
}

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        contentAlignment = androidx.compose.ui.Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorScreen(errorMessage: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = androidx.compose.ui.Alignment.Center
    ) {
        Text(errorMessage, color = Color.Red)
    }
}

@Composable
fun RecipeList(recipes: List<Recipe>) {

    LazyColumn {
        items(recipes.size) { index ->
            if (index > 0) {
                Spacer(modifier = Modifier.height(5.dp))
            }
            RecipeItem(recipe = recipes[index])
        }
    }
}