package com.example.myapplication.arunproject.presentation.view.state

import com.example.myapplication.arunproject.data.model.Recipe

sealed class RecipeViewState {
    data object Loading : RecipeViewState()
    data class Success(
        val recipes: List<Recipe>,
        val selectedRecipeId: String? = null,
        val isShowGrid: Boolean = false,
        val searchQuery: String = "",
        val cart : List<String> = emptyList()
    ) : RecipeViewState()

    data class Error(val message: String) : RecipeViewState()
}