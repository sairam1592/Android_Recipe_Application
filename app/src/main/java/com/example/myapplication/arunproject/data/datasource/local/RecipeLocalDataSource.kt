package com.example.myapplication.arunproject.data.datasource.local

import com.example.myapplication.arunproject.data.model.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipeLocalDataSource {
    fun getRecipes(): Flow<List<Recipe>>
    suspend fun saveRecipes(recipes: List<Recipe>)
    fun getRecipeById(recipeId: String): Flow<Recipe>
}