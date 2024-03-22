package com.example.myapplication.arunproject.presentation.view

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.arunproject.presentation.view.compose.RecipeScreen
import com.example.myapplication.arunproject.presentation.viewmodel.RecipeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class RecipeActivity : AppCompatActivity() {
    private val recipeViewModel: RecipeViewModel by viewModels()

    @ExperimentalCoroutinesApi
    @ExperimentalMaterialApi
    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            recipeViewModel.recipeDetailState.collect { result ->
                withContext(Dispatchers.Main) {
                    //TODO LOGIC TO FETCH FROM DB WORKS, MOVE RELEVANT CLASSES TO RECIPE DETAILS SCREEN
                }
            }
        }

        setContent {
            RecipeScreen(
                recipeViewModel = recipeViewModel,
                isShowAdaptiveGrid = false, viewRecipe = { recipeId ->
                    //TODO TRIGGER NAVIGATION TO RECIPE DETAILS SCREEN
                }
            )
        }
    }
}