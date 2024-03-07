package com.example.myapplication.arunproject.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.arunproject.common.AppConstants.NO_INTERNET_ERROR_MESSAGE
import com.example.myapplication.arunproject.domain.model.DataResult
import com.example.myapplication.arunproject.domain.usecase.GetRecipeByIdUseCase
import com.example.myapplication.arunproject.domain.usecase.GetRecipesUseCase
import com.example.myapplication.arunproject.presentation.view.state.RecipeViewState
import com.example.myapplication.recipescreen.presentation.view.state.RecipeDetailUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val getRecipesUseCase: GetRecipesUseCase,
    private val getRecipeByIdUseCase: GetRecipeByIdUseCase,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _recipeState = MutableStateFlow<RecipeViewState>(RecipeViewState.Loading)
    val recipeState: StateFlow<RecipeViewState> = _recipeState.asStateFlow()

    private val _recipeDetailState = MutableStateFlow(RecipeDetailUIState())
    val recipeDetailState = _recipeDetailState.asStateFlow()

    init {
        loadRecipes()
    }

    /**
     * This function fetches the recipes.
     */
    fun loadRecipes() {
        viewModelScope.launch(ioDispatcher) {
            _recipeState.value = RecipeViewState.Loading
            getRecipesUseCase().collect { dataResult ->
                when (dataResult) {
                    is DataResult.Success -> _recipeState.value =
                        RecipeViewState.Success(dataResult.data)

                    is DataResult.Error -> _recipeState.value = RecipeViewState.Error(
                        dataResult.exception.message ?: NO_INTERNET_ERROR_MESSAGE
                    )
                }
            }
        }
    }

    fun getRecipeById(recipeId: String) {
        viewModelScope.launch {
            getRecipeByIdUseCase(recipeId).collect { result ->
                _recipeDetailState.value = _recipeDetailState.value.copy(recipeDetail = result)
            }
        }
    }

    /**
     * This function selects a recipe.
     * @param recipeId The ID of the recipe to be selected.
     */
    fun onRecipeSelected(selectedIdFromState: String?, recipeId: String) {
        val currentState = _recipeState.value

        if (currentState is RecipeViewState.Success) {
            _recipeState.value =
                currentState.copy(selectedRecipeId = if (selectedIdFromState == recipeId) null else recipeId)
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