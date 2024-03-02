package com.example.myapplication.arunproject.data.repository

import com.example.myapplication.arunproject.data.model.Recipe

/**
 * This is a repository interface that provides access to recipes.
 */
interface RecipeRepository {
    suspend fun getRecipes(): Result<List<Recipe>>
}