package com.example.myapplication.arunproject.data.repository

import com.example.myapplication.arunproject.data.datasource.local.RecipeLocalDataSource
import com.example.myapplication.arunproject.data.datasource.remote.RecipeRemoteDataSource
import com.example.myapplication.arunproject.data.model.Recipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val recipeRemoteDataSource: RecipeRemoteDataSource,
    private val recipeLocalDataSource: RecipeLocalDataSource
) :
    RecipeRepository {
    override suspend fun getRecipes(): Flow<List<Recipe>> = flow {
        emitAll(recipeLocalDataSource.getRecipes().take(1).filter { it.isNotEmpty() })

        val localRecipes = recipeLocalDataSource.getRecipes().firstOrNull()
        if (localRecipes.isNullOrEmpty()) {
            try {
                val remoteRecipes = recipeRemoteDataSource.getRecipes().getOrThrow()
                recipeLocalDataSource.saveRecipes(remoteRecipes)
                emit(remoteRecipes)
            } catch (e: Exception) {
                emit(emptyList())
            }
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getRecipeById(recipeId: String): Flow<Recipe?> = flow {
        val localRecipe = recipeLocalDataSource.getRecipeById(recipeId).first()
        emit(localRecipe)
    }
}