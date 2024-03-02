package com.example.myapplication.arunproject.domain.usecase

import com.example.myapplication.arunproject.data.model.Recipe
import com.example.myapplication.arunproject.data.repository.RecipeRepository
import javax.inject.Inject

/**
 * This is a use case that gets recipes.
 */
class GetRecipesUseCase @Inject constructor(private val repository: RecipeRepository) {
    suspend operator fun invoke(): Result<List<Recipe>> {
        return repository.getRecipes()
    }
}