# Quiz App

This project is a simple Quiz application developed using Kotlin and Jetpack Compose. The app is designed to provide an interactive quiz experience with smooth navigation and modern UI components.

## Technologies Used

### Front-End
- **Kotlin**: Primary language for Android app development.
- **Jetpack Compose**: A modern toolkit for building native Android UI.
- **Coroutines**: For managing background tasks and asynchronous operations.
- **Flow**: To handle reactive data streams efficiently.
- **Android Architecture Components**: Includes ViewModel to separate UI logic from business logic.
- **Jetpack Compose Navigation**: For seamless navigation between quiz screens.
- **DataStore**: To store user preferences and quiz settings.
- **Material Components for Android**: Provides modern, responsive design elements.

### Back-End
- **Ktor-client**: Used for making API requests to fetch quiz data (generated via [mocki.io](https://mocki.io)).

## Architecture
- **MVVM (Model-View-ViewModel)**: Ensures separation of concerns and allows for better maintainability and scalability of the app.

## Dependency Injection
- **Dagger-Hilt**: To manage dependencies efficiently and simplify the DI setup.

## Database
- **Room**: For local database management, storing quiz results, and user progress.

## Minimum Requirements
- **Android Studio Version**: Android Studio Hedgehog | 2023.1.1 or later.

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/quiz-app.git
