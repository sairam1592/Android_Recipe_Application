package com.example.myapplication.arunproject.data.repository

import com.example.myapplication.arunproject.data.model.Recipe

/**
 * This is a repository interface that provides access to recipes.
 */
class RecipeRepositoryImpl() : RecipeRepository {
    override suspend fun getRecipes(): Result<List<Recipe>> {
        return TODO("Not yet implemented")
    }
}