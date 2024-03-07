package com.example.myapplication.arunproject.domain.usecase

import com.example.myapplication.arunproject.data.model.Recipe
import com.example.myapplication.arunproject.data.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRecipeByIdUseCase @Inject constructor(private val repository: RecipeRepository) {
    suspend operator fun invoke(recipeId: String): Flow<Recipe?> =
        repository.getRecipeById(recipeId)
}