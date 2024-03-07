package com.example.myapplication.arunproject.data.datasource.remote

import com.example.myapplication.arunproject.data.model.Recipe
import com.example.myapplication.arunproject.domain.model.DataResult

interface RecipeRemoteDataSource {
    suspend fun getRecipes(): DataResult<List<Recipe>>
}
