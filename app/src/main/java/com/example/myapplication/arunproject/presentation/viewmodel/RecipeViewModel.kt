package com.example.myapplication.arunproject.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.arunproject.common.AppConstants.ERROR_MESSAGE
import com.example.myapplication.arunproject.domain.model.DataResult
import com.example.myapplication.arunproject.domain.usecase.GetRecipesUseCase
import com.example.myapplication.arunproject.presentation.view.state.RecipeViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * This is a view model that provides access to recipes.
 * @param getRecipesUseCase The use case that provides a list of recipes.
 */
@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val getRecipesUseCase: GetRecipesUseCase,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _recipeState = MutableStateFlow<RecipeViewState>(RecipeViewState.Loading)
    val recipeState: StateFlow<RecipeViewState> = _recipeState.asStateFlow()

    init {
        loadRecipes()
    }

    private fun loadRecipes() {
        viewModelScope.launch(ioDispatcher) {
            _recipeState.value = RecipeViewState.Loading
            val result = runCatching { getRecipesUseCase() }
            result.onSuccess { dataResult ->
                when (dataResult) {
                    is DataResult.Success -> _recipeState.value =
                        RecipeViewState.Success(dataResult.data)

                    is DataResult.Error -> _recipeState.value =
                        RecipeViewState.Error(dataResult.exception.message ?: ERROR_MESSAGE)
                }
            }
            result.onFailure { error ->
                _recipeState.value = RecipeViewState.Error(error.message ?: ERROR_MESSAGE)
            }
        }
    }

    // Function to handle recipe selection
    fun onRecipeSelected(recipeId: String) {
        // Set the selected recipe ID in your state
        val currentState = _recipeState.value
        if (currentState is RecipeViewState.Success) {
            _recipeState.value = currentState.copy(selectedRecipeId = recipeId)
        }
    }

    // Function to clear the selected recipe ID
    fun clearSelectedRecipeId() {
        val currentState = _recipeState.value
        if (currentState is RecipeViewState.Success) {
            _recipeState.value = currentState.copy(selectedRecipeId = null)
        }
    }

    /**
     * This function toggles the grid view.
     * @param isShowGrid The flag to show grid view.
     */
    fun showGridList(isShowGrid: Boolean) {
        val currentState = _recipeState.value
        if (currentState is RecipeViewState.Success) {
            _recipeState.value = currentState.copy(isShowGrid = isShowGrid)
        }
    }
}