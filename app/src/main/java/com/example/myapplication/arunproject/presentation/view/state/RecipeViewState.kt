package com.example.myapplication.arunproject.presentation.view.state

import com.example.myapplication.arunproject.data.model.Recipe

sealed class RecipeViewState {
    object Loading : RecipeViewState()
    data class Success(val recipes: List<Recipe>) : RecipeViewState()
    data class Error(val message: String) : RecipeViewState()
}