package com.example.myapplication.arunproject.data.datasource.remote

import com.example.myapplication.arunproject.data.model.Recipe
interface RecipeRemoteDataSource {
    suspend fun getRecipes(): Result<List<Recipe>>
}
