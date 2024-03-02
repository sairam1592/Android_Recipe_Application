package com.example.myapplication.arunproject.data.repository

import com.example.myapplication.arunproject.data.datasource.remote.RecipeRemoteDataSourceImpl
import com.example.myapplication.arunproject.data.model.Recipe
import javax.inject.Inject

/**
 * This is a repository interface that provides access to recipes.
 */
class RecipeRepositoryImpl @Inject constructor(private val recipeRemoteDataSourceImpl: RecipeRemoteDataSourceImpl) :
    RecipeRepository {
    override suspend fun getRecipes(): Result<List<Recipe>> {
        return TODO("Not yet implemented")
    }
}