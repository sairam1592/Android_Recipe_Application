package com.example.myapplication.arunproject.common

object AppConstants {
    const val DATE_FORMAT = "dd MMM yyyy"

    //API Constants
    const val BASE_URL = "https://hf-android-app.s3-eu-west-1.amazonaws.com/android-test/"
    const val API_GET_PARAM =
        "recipes.json" // This is the parameter for the API call, can be modified to test error response

    //Error Messages
    const val NO_INTERNET_ERROR_MESSAGE = "Check your Internet Connection and try again."
    const val ERROR_API_FAILED = "Failed to fetch recipes, please try again later."
    const val RETRY = "Retry"

    const val CHOOSE_TEXT = "Choose a Recipe"
    const val ADD_TO_CART = "Add To Cart"
    const val RECIPE_ROW = "Recipe Row"
    const val RECIPE_COLUMN = "Recipe Column"

    const val MY_INTRO =
        "Welcome to Recipe Realm Android app. This recipe app is built using" +
                " Kotlin, Jetpack Compose, Kotlin Coroutines, Hilt, Retrofit etc." +
                " following the best practices and design patterns such as " +
                " MVVM, Clean Architecture, SOLID principles." +
                "\n Check it out, Thanks for visiting."
}