package com.example.myapplication.arunproject.data.repository

import com.example.myapplication.arunproject.data.model.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    suspend fun getRecipes(): Flow<List<Recipe>>

    suspend fun getRecipeById(recipeId: String): Flow<Recipe?>
}