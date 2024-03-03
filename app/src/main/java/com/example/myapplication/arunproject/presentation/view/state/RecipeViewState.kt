package com.example.myapplication.arunproject.presentation.view.state

import com.example.myapplication.arunproject.data.model.Recipe

/**
 * Recipe view state
 */
sealed class RecipeViewState {
    object Loading : RecipeViewState()
    data class Success(
        val recipes: List<Recipe>,
        val selectedRecipeId: String? = null,
        val isShowGrid: Boolean = false,
    ) : RecipeViewState()

    data class Error(val message: String) : RecipeViewState()
}