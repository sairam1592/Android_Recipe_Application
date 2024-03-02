package com.example.myapplication.arunproject.presentation.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.arun.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.arunproject.presentation.view.state.RecipeViewState
import com.example.myapplication.arunproject.presentation.viewmodel.RecipeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RecipeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: RecipeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            viewModel.recipeState.collect { state ->
                when (state) {
                    is RecipeViewState.Loading -> {
                        // Show loading UI
                    }

                    is RecipeViewState.Success -> {
                        val recipes = state.recipes
                        // Update UI with recipes
                    }

                    is RecipeViewState.Error -> {
                        val errorMessage = state.message
                        // Show error UI
                    }
                }
            }
        }
    }
}