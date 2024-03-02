package com.example.myapplication.arunproject.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.myapplication.arunproject.domain.usecase.GetRecipesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * This is a view model that provides access to recipes.
 * @param getRecipesUseCase The use case that provides a list of recipes.
 */
@HiltViewModel
class RecipeViewModel @Inject constructor(private val getRecipesUseCase: GetRecipesUseCase) :
    ViewModel() {

}