package com.example.myapplication.arunproject.data.network

import com.example.myapplication.arunproject.common.AppConstants.API_GET_PARAM
import com.example.myapplication.arunproject.data.model.Recipe
import retrofit2.Response
import retrofit2.http.GET

/**
 * This is a service interface that provides access to recipes.
 */
interface RecipeService {
    @GET(API_GET_PARAM)
    suspend fun getRecipes(): Response<List<Recipe>>
}