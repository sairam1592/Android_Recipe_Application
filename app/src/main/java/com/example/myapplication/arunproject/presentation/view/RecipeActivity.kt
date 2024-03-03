package com.example.myapplication.arunproject.presentation.view

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.ExperimentalComposeUiApi
import com.example.myapplication.arunproject.presentation.view.compose.RecipeScreenVertical
import com.example.myapplication.arunproject.presentation.viewmodel.RecipeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
class RecipeActivity : AppCompatActivity() {
    private val viewModel: RecipeViewModel by viewModels()

    @ExperimentalCoroutinesApi
    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RecipeScreenVertical(recipeViewModel = viewModel, isShowGrid = true, isShowAdaptiveGrid = false)
        }
    }
}