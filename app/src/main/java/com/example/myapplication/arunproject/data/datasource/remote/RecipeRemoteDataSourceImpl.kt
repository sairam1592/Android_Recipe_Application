package com.example.myapplication.arunproject.data.datasource.remote

import com.example.myapplication.arunproject.data.model.Recipe
import com.example.myapplication.arunproject.data.network.RecipeService
import javax.inject.Inject

/**
 * This is a data source implementation that provides access to recipes.
 */
class RecipeRemoteDataSourceImpl @Inject constructor(private val recipeService: RecipeService) :
    RecipeRemoteDataSource {
    override suspend fun getRecipes(): Result<List<Recipe>> {
        return TODO("return the result of the getRecipes method from the recipeService")
    }
}