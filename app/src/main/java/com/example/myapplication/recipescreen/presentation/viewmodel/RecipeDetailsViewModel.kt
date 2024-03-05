package com.example.myapplication.recipescreen.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.recipescreen.common.RecipeScreenConstants
import com.example.myapplication.recipescreen.domain.usecase.GetRecipeDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailsViewModel @Inject constructor(
    private val getRecipeDetailUseCase: GetRecipeDetailUseCase
) : ViewModel() {

    private val _recipeDetails = MutableStateFlow<String?>("")
    val recipeDetails: StateFlow<String?> = _recipeDetails.asStateFlow()

    init {
        getRecipeDetails(RecipeScreenConstants.TEST_RECIPE_ID)
    }

    private fun getRecipeDetails(recipeId: String) {
        viewModelScope.launch {
            getRecipeDetailUseCase(recipeId).collect { result ->
                _recipeDetails.value = result
            }
        }
    }
}