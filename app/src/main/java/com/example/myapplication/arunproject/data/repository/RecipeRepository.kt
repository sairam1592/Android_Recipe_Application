package com.example.myapplication.arunproject.data.repository

import com.example.myapplication.arunproject.data.model.Recipe
import com.example.myapplication.arunproject.domain.model.DataResult
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    suspend fun getRecipes(): Flow<DataResult<List<Recipe>>>

    suspend fun getRecipeById(recipeId: String): Flow<Recipe?>
}