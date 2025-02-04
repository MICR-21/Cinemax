# Movie Application

This is an Android application built with **Jetpack Compose** that displays the latest and upcoming movies using the [The Movie Database (TMDB) API](https://developer.themoviedb.org/reference/intro/getting-started). 
The app includes onboarding screens, a home screen with a list of movies, and a detail screen for more information about each movie.
## Features
- **Onboarding Screens**: Displays three onboarding screens before navigating to the main app.
- **Home Screen**: Shows a list of the latest and upcoming movies.
- **Movie Detail Screen**: Provides detailed information about a selected movie.
- **Navigation**: Smooth navigation between screens using Jetpack Navigation.
- **API Integration**: Fetches movie data from the TMDB API using Retrofit.
---

## Prerequisites

Before running the project, ensure you have the following:

1. **Android Studio**: Download and install the latest version of [Android Studio](https://developer.android.com/studio).
2. **TMDB API Key**: Sign up at [The Movie Database (TMDB)](https://www.themoviedb.org/signup) and generate an API key.
3. **Android Device or Emulator**: You can use a physical device or an emulator to run the app.

## Setup Instructions

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/movie-application.git
cd movie-application
```

### 2. Add Your TMDB API Key

1. Open the project in Android Studio.
2. Locate the `MovieViewModel.kt` file.
3. Replace `"YOUR_API_KEY"` with your actual TMDB API key:

   ```kotlin
   val latestMovies = RetrofitInstance.api.getLatestMovies("YOUR_API_KEY")
   val upcomingMovies = RetrofitInstance.api.getUpcomingMovies("YOUR_API_KEY")
   ```

### 3. Run the App

1. Connect your Android device or start an emulator.
2. Click **Run** in Android Studio or use the shortcut `Shift + F10`.
3. The app will build and launch on your device/emulator.

---

## Dependencies

The project uses the following dependencies:

- **Jetpack Compose**: For building the UI.
- **Retrofit**: For making API calls to TMDB.
- **Coil**: For loading images.
- **Navigation Compose**: For handling navigation between screens.

You can find the full list of dependencies in the `build.gradle` file.

---

## Contributing

Contributions are welcome! If you'd like to contribute, please follow these steps:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature/YourFeatureName`).
3. Commit your changes (`git commit -m 'Add some feature'`).
4. Push to the branch (`git push origin feature/YourFeatureName`).
5. Open a pull request.

---

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

## Acknowledgments

- [The Movie Database (TMDB)](https://www.themoviedb.org/) for providing the movie data API.
- [Jetpack Compose](https://developer.android.com/jetpack/compose) for the modern UI toolkit.
- [Retrofit](https://square.github.io/retrofit/) for simplifying API calls.

---

Enjoy the app! 🎬🍿

---
