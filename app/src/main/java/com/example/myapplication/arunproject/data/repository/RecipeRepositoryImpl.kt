package com.example.myapplication.arunproject.data.repository

import com.example.myapplication.arunproject.data.datasource.local.RecipeLocalDataSource
import com.example.myapplication.arunproject.data.datasource.remote.RecipeRemoteDataSource
import com.example.myapplication.arunproject.data.model.Recipe
import com.example.myapplication.arunproject.domain.model.DataResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val recipeRemoteDataSource: RecipeRemoteDataSource,
    private val recipeLocalDataSource: RecipeLocalDataSource
) :
    RecipeRepository {
    override suspend fun getRecipes(): Flow<DataResult<List<Recipe>>> = flow {
        try {
            val localRecipes = withContext(Dispatchers.IO) {
                recipeLocalDataSource.getRecipes().first()
            }
            if (localRecipes.isNotEmpty()) {
                emit(DataResult.Success(localRecipes))
            } else {
                val remoteRecipesResult = withContext(Dispatchers.IO) {
                    recipeRemoteDataSource.getRecipes()
                }
                when (remoteRecipesResult) {
                    is DataResult.Success -> {
                        recipeLocalDataSource.saveRecipes(remoteRecipesResult.data)
                        emit(DataResult.Success(remoteRecipesResult.data))
                    }

                    is DataResult.Error -> {
                        emit(remoteRecipesResult)
                    }
                }
            }
        } catch (e: Exception) {
            emit(DataResult.Error(e))
        }
    }.flowOn(Dispatchers.Default)

    override suspend fun getRecipeById(recipeId: String): Flow<Recipe?> = flow {
        val localRecipe = recipeLocalDataSource.getRecipeById(recipeId).first()
        emit(localRecipe)
    }
}