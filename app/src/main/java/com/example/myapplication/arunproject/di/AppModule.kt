package com.example.myapplication.arunproject.di

import com.example.myapplication.arunproject.common.AppConstants
import com.example.myapplication.arunproject.data.datasource.remote.RecipeRemoteDataSource
import com.example.myapplication.arunproject.data.datasource.remote.RecipeRemoteDataSourceImpl
import com.example.myapplication.arunproject.data.network.RecipeService
import com.example.myapplication.arunproject.data.repository.RecipeRepository
import com.example.myapplication.arunproject.data.repository.RecipeRepositoryImpl
import com.example.myapplication.arunproject.domain.usecase.GetRecipesUseCase
import com.example.myapplication.arunproject.presentation.viewmodel.RecipeViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * This is a module that provides dependencies for the app.
 */
@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Provides
    @Singleton
    fun provideRecipeService(retrofit: Retrofit): RecipeService {
        return retrofit.create(RecipeService::class.java)
    }

    @Provides
    @Singleton
    fun provideRecipeRemoteDataSource(recipeService: RecipeService): RecipeRemoteDataSource {
        return RecipeRemoteDataSourceImpl(recipeService)
    }

    @Provides
    fun provideRecipeRepository(recipeRemoteDataSourceImpl: RecipeRemoteDataSourceImpl): RecipeRepository {
        return RecipeRepositoryImpl(recipeRemoteDataSourceImpl)
    }

    @Provides
    fun provideGetRecipesUseCase(repository: RecipeRepository): GetRecipesUseCase {
        return GetRecipesUseCase(repository)
    }

    @Provides
    fun provideRecipeViewModel(
        getRecipesUseCase: GetRecipesUseCase,
        ioDispatcher: CoroutineDispatcher
    ): RecipeViewModel {
        return RecipeViewModel(getRecipesUseCase, ioDispatcher)
    }
}