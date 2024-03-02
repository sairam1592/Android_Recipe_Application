package com.example.myapplication.arunproject.data.datasource.remote

import com.example.myapplication.arunproject.data.model.Recipe
import com.example.myapplication.arunproject.domain.model.DataResult

/**
 * This is a data source interface that provides access to recipes.
 */
interface RecipeRemoteDataSource {
    suspend fun getRecipes(): DataResult<List<Recipe>>
}
