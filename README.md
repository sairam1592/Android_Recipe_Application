# My Android Compose App

Welcome to the repository of my latest Android application, where modern design meets seamless functionality. This project is a testament to the best practices in software development, featuring Clean Code, Clean Architecture, and adherence to SOLID principles.

## About the Project

This app has been passionately crafted to demonstrate the full spectrum of my development skills. It is built using Kotlin and leverages Kotlin Coroutines for efficient background processing, implementing State, Flow, and Dispatchers to handle API calls and data management with finesse.

## Key Features

- **Clean Architecture**: The codebase is structured on Clean Architecture principles, ensuring scalability, maintainability, and a clear separation of concerns.
- **SOLID Principles**: Every module within this application adheres to SOLID principles, promoting a robust and adaptable codebase.
- **Modern UI**: Utilizing Jetpack Compose, the app presents a modern UI that is both performant and aesthetically pleasing.
- **Kotlin Coroutines & Flow**: For asynchronous tasks and real-time data updates, Kotlin Coroutines and Flow are employed effectively.
- **State Management**: Reactive state management is implemented, ensuring that the UI is always in sync with the underlying data.

## Functionality
- Fetch a list of Recipe from a WEB URL and show it in the UI
- Leveraged Kotlin Coroutines, StateFlow for API and passing of data back to view
- Used Jetpack Compose to Populate list of data into the composable UI screen
- This UI screen can load both a vertical list and also a grid of recipe
- Snow SnackBar on click of an Item

## Upcoming Additions:
- A lot of Jetpack design implementation
- Room DB for caching
- More features such as Recipe Detail screen, Difficulty level of recipe, Refer to youtube link, Select/Unselect a Recipe, Multiple Select/Unselect just like in other food apps
- Unit Testing using Junit5 and Mockk

## Screenshots

<img width="323" alt="Screenshot 2024-03-02 at 7 53 25 PM" src="https://github.com/sairam1592/Android_Recipe_Application/assets/14980927/19ed87ad-8319-450d-8681-5cab8253ccc3">

![Screenshot 2024-03-03 at 12 28 15 PM](https://github.com/sairam1592/Android_Recipe_Application/assets/14980927/b809df37-3aba-4fb7-8af1-3aad816492d0)

**LIST POPULATED AS A SIMPLE VERTICAL SCROLL

https://github.com/sairam1592/Android_Recipe_Application/assets/14980927/ca5e2814-f89c-403c-ad1c-6ef0359e1ec3

**LIST POPULATED AS A GRID

https://github.com/sairam1592/Android_Recipe_Application/assets/14980927/a258da7c-5827-4080-a416-28f14caceaa0



## Technologies & Libraries

The project is built with the latest tools and libraries in the Android ecosystem:

- **Kotlin**: The whole application is written in Kotlin for its conciseness and reliability.
- **Jetpack Compose**: Leveraged for building native UI, showcasing my UI developmental skills.
- **Retrofit**: For networking, making API requests and handling responses.
- **Hilt**: For dependency injection, simplifying the architecture and making testing easier.
- **Lottie**: To add rich animations that enhance the user experience.
- **Coil**: Used for image loading, making use of Kotlin's Coroutines for efficiency.
- **AndroidX & Material Components**: For the latest UI components that adhere to Material Design principles.
- **Coroutines**: To handle asynchronous tasks and provide a smooth user experience.


## Building the Project

To build this project, clone the repo and import it into Android Studio. Make sure you have the latest version of Android Studio and the Kotlin plugin installed.
