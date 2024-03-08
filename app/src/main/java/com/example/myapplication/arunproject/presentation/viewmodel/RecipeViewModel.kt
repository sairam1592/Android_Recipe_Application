package com.example.myapplication.arunproject.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.arunproject.common.AppConstants.NO_INTERNET_ERROR_MESSAGE
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

    private val _recipeState = MutableStateFlow(RecipeViewState())
    val recipeState: StateFlow<RecipeViewState> = _recipeState.asStateFlow()

    private val _recipeDetailState = MutableStateFlow(RecipeDetailUIState())
    val recipeDetailState = _recipeDetailState.asStateFlow()

    init {
        loadRecipes()
    }

    fun loadRecipes() {
        viewModelScope.launch(ioDispatcher) {
            _recipeState.value = _recipeState.value.copy(isLoading = true)
            try {
                getRecipesUseCase().collect { recipes ->
                    _recipeState.value =
                        _recipeState.value.copy(recipes = recipes, isLoading = false)
                }
            } catch (e: Exception) {
                _recipeState.value = _recipeState.value.copy(
                    errorMessage = NO_INTERNET_ERROR_MESSAGE,
                    isLoading = false
                )
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

    fun onRecipeSelected(selectedIdFromState: String?, recipeId: String) {
        _recipeState.value =
            _recipeState.value.copy(selectedRecipeId = if (selectedIdFromState == recipeId) null else recipeId)
    }

    fun addToCart(recipeId: String) {
        val cart = _recipeState.value.cart.toMutableList()
        cart.add(recipeId)
        _recipeState.value = _recipeState.value.copy(cart = cart)
    }

    fun clearCart() {
        _recipeState.value = _recipeState.value.copy(cart = emptyList())
    }

    fun clearSelectedRecipeId() {
        _recipeState.value = _recipeState.value.copy(selectedRecipeId = null)
    }

    fun showGridList(isShowGrid: Boolean) {
        _recipeState.value = _recipeState.value.copy(isShowGrid = isShowGrid)
    }

    fun searchRecipe(query: String) {
        _recipeState.value = _recipeState.value.copy(searchQuery = query)
    }
}