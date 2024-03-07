package com.example.myapplication.arunproject.data.datasource.local

import com.example.myapplication.arunproject.common.throwExceptionIfNull
import com.example.myapplication.arunproject.data.model.Recipe
import com.example.myapplication.arunproject.data.model.db.RecipeDao
import com.example.myapplication.arunproject.domain.mapper.RecipeMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RecipeLocalDataSourceImpl @Inject constructor(private val recipeDao: RecipeDao) :
    RecipeLocalDataSource {

    override fun getRecipes(): Flow<List<Recipe>> = recipeDao.getAllRecipes().map { entities ->
        entities.map(RecipeMapper::entityToDomain)
    }

    override suspend fun saveRecipes(recipes: List<Recipe>) {
        recipeDao.insertAll(recipes.map(RecipeMapper::domainToEntity))
    }

    override fun getRecipeById(recipeId: String): Flow<Recipe> =
        recipeDao.getRecipeById(recipeId).map(RecipeMapper::entityToDomain).throwExceptionIfNull()
}