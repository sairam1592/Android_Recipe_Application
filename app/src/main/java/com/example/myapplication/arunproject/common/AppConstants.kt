package com.example.myapplication.arunproject.common

object AppConstants {
    const val DATE_FORMAT = "dd MMM yyyy"
    const val BASE_URL = "https://hf-android-app.s3-eu-west-1.amazonaws.com/android-test/"
    const val ERROR_MESSAGE = "An error occurred, please try again later."
    const val ERROR_API_FAILED = "Failed to fetch recipes, please try again later."
    const val API_GET_PARAM =
        "recipes.json" // This is the parameter for the API call, can be modified to test error response

    const val MY_INTRO =
        "Welcome to my Android app, i am Arun. I am building a simple recipe app. I hope you like it. " +
                "\n I am using Kotlin, Jetpack Compose, Kotlin Coroutines, Hilt, Retrofit etc." +
                "\n I am trying to follow the best practices and design patterns such as. " +
                "MVVM, Clean Architecture, SOLID principles. " +
                "\n Check it out, Thanks for visiting."
}