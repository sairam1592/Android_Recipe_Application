package com.example.myapplication.arunproject.domain.usecase

import com.example.myapplication.arunproject.data.model.Recipe
import com.example.myapplication.arunproject.data.repository.RecipeRepository

/**
 * This is a use case that gets recipes.
 */
class GetRecipesUseCase(private val repository: RecipeRepository) {
    suspend operator fun invoke(): Result<List<Recipe>> {
        return repository.getRecipes()
    }
}