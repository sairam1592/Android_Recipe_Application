package com.example.myapplication.recipescreen.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    private val _recipeDetails = MutableStateFlow<List<String>>(emptyList())
    val recipeDetails: StateFlow<List<String>> = _recipeDetails.asStateFlow()

    fun getRecipeDetails(recipeId: String) {
        viewModelScope.launch {
            getRecipeDetailUseCase(recipeId).collect { result ->
                _recipeDetails.value = result
            }
        }
    }
}