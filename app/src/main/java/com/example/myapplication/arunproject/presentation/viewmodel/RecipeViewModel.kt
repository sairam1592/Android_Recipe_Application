package com.example.myapplication.arunproject.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.arunproject.common.AppConstants.NO_INTERNET_ERROR_MESSAGE
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

    /**
     * This function fetches the recipes.
     */
    fun loadRecipes() {
        viewModelScope.launch(ioDispatcher) {
            _recipeState.value = RecipeViewState.Loading
            val result = runCatching { getRecipesUseCase() }
            result.onSuccess { dataResult ->
                when (dataResult) {
                    is DataResult.Success -> _recipeState.value =
                        RecipeViewState.Success(dataResult.data)

                    is DataResult.Error -> _recipeState.value =
                        RecipeViewState.Error(
                            dataResult.exception.message ?: NO_INTERNET_ERROR_MESSAGE
                        )
                }
            }
            result.onFailure { error ->
                _recipeState.value =
                    RecipeViewState.Error(error.message ?: NO_INTERNET_ERROR_MESSAGE)
            }
        }
    }

    /**
     * This function selects a recipe.
     * @param recipeId The ID of the recipe to be selected.
     */
    fun onRecipeSelected(recipeId: String) {
        // Set the selected recipe ID in your state
        val currentState = _recipeState.value
        if (currentState is RecipeViewState.Success) {
            _recipeState.value = currentState.copy(selectedRecipeId = recipeId)
        }
    }

    /**
     * This function adds a recipe to the cart.
     * @param recipeId The ID of the recipe to be added.
     */
    fun addToCart(recipeId: String) {
        val currentState = _recipeState.value
        if (currentState is RecipeViewState.Success) {
            val cart = currentState.cart.toMutableList()
            cart.add(recipeId)
            _recipeState.value = currentState.copy(cart = cart)
        }
    }

    /**
     * This function removes a recipe from the cart.
     * @param recipeId The ID of the recipe to be removed.
     */
    fun clearCart() {
        val currentState = _recipeState.value
        if (currentState is RecipeViewState.Success) {
            _recipeState.value = currentState.copy(cart = emptyList())
        }
    }

    /**
     * This function clears the selected recipe ID.
     */
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

    /**
     * This function searches the recipe.
     * @param query The search query.
     */
    fun searchRecipe(query: String) {
        val currentState = _recipeState.value
        if (currentState is RecipeViewState.Success) {
            _recipeState.value = currentState.copy(searchQuery = query)
        }
    }
}