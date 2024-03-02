package com.example.myapplication.arunproject.data.repository

import com.example.myapplication.arunproject.data.model.Recipe
import com.example.myapplication.arunproject.domain.model.DataResult

/**
 * This is a repository interface that provides access to recipes.
 */
interface RecipeRepository {
    suspend fun getRecipes(): DataResult<List<Recipe>>
}