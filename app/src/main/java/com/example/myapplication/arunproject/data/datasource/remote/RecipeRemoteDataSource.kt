package com.example.myapplication.arunproject.data.datasource.remote

import com.example.myapplication.arunproject.data.model.Recipe

/**
 * This is a data source interface that provides access to recipes.
 */
interface RecipeRemoteDataSource {
    suspend fun getRecipes(): Result<List<Recipe>>
}
