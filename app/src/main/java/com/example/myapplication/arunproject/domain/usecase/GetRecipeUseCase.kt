package com.example.myapplication.arunproject.domain.usecase

import com.example.myapplication.arunproject.data.model.Recipe
import com.example.myapplication.arunproject.data.repository.RecipeRepository
import com.example.myapplication.arunproject.domain.model.DataResult
import javax.inject.Inject

/**
 * This is a use case that gets recipes.
 */
class GetRecipesUseCase @Inject constructor(private val repository: RecipeRepository) {
    suspend operator fun invoke(): DataResult<List<Recipe>> {
        return repository.getRecipes()
    }
}