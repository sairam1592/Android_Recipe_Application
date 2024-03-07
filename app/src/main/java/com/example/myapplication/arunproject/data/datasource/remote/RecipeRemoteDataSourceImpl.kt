package com.example.myapplication.arunproject.data.datasource.remote

import com.example.myapplication.arunproject.common.AppConstants.ERROR_API_FAILED
import com.example.myapplication.arunproject.data.model.Recipe
import com.example.myapplication.arunproject.data.network.RecipeService
import com.example.myapplication.arunproject.domain.model.DataResult
import javax.inject.Inject

/**
 * This is a data source implementation that provides access to recipes.
 */
class RecipeRemoteDataSourceImpl @Inject constructor(private val recipeService: RecipeService) :
    RecipeRemoteDataSource {

    override suspend fun getRecipes(): DataResult<List<Recipe>> = try {
        val response = recipeService.getRecipes()
        if (response.isSuccessful) {
            DataResult.Success(response.body() ?: emptyList())
        } else {
            DataResult.Error(Exception(ERROR_API_FAILED))
        }
    } catch (e: Exception) {
        DataResult.Error(e)
    }
}