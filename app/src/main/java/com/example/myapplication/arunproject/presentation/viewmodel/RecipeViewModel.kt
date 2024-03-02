package com.example.myapplication.arunproject.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.myapplication.arunproject.domain.usecase.GetRecipesUseCase

/**
 * This is a view model that provides access to recipes.
 * @param getRecipesUseCase The use case that provides a list of recipes.
 */
class RecipeViewModel(private val getRecipesUseCase: GetRecipesUseCase) : ViewModel() {

}