package com.example.myapplication.arunproject.data.datasource.remote

import com.example.myapplication.arunproject.common.AppConstants.ERROR_API_FAILED
import com.example.myapplication.arunproject.data.model.Recipe
import com.example.myapplication.arunproject.data.network.RecipeService
import javax.inject.Inject

class RecipeRemoteDataSourceImpl @Inject constructor(private val recipeService: RecipeService) :
    RecipeRemoteDataSource {
    override suspend fun getRecipes(): Result<List<Recipe>> = try {
        val response = recipeService.getRecipes()
        if (response.isSuccessful) {
            Result.success(response.body() ?: emptyList())
        } else {
            Result.failure(Exception(ERROR_API_FAILED))
        }
    } catch (e: Exception) {
        Result.failure(e)
    }
}