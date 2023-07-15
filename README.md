# News_App

News App is a simple news app which uses [NewsAPI](https://newsapi.org/) to fetch top and breaking news headlines from the API. The main aim of this app is to be a leading example of how to build Modern Android applications for all Android Developers


The idea is to keep the app super simple while demonstrating new libraries and tools which makes it easier to build high quality Android applications.


# Demo
<p align="inline" width="100%">
    <img  src="https://j.gifs.com/jYwALz.gif" width="200" height="480">
    <img  src="https://j.gifs.com/57vA3Z.gif" width="200" height="480">
    <img src="https://github.com/AyanChaudhary/News_App/assets/112795104/19106103-490f-48af-b66f-e1a1c0b9922f" width="420" height="480" />
</p>

## Installation

News App uses  [NewsAPI](https://newsapi.org/) to fetch the news data. To run this app, you'll need News API key. This is entirely free. To do so :

1. Go to [this](https://newsapi.org/) website 
2. Login or create an account
3. Click on GETAPIKEY
4. Open the project, and go to the `NewsAPI.kt` file.
5. Replace `const val API_KEY="" ` by your key.


## Tools and Services Used

Different tools and services used for building this application includes:


- [Kotlin](https://kotlinlang.org/) 
- MVVM architecture pattern
- Navigation components
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) 
- [Dagger 2](https://developer.android.com/training/dependency-injection) for dependency injection 
- [Room Database](https://developer.android.com/training/data-storage/room) for saving favourite news
- [Retrofit](https://square.github.io/retrofit/) for handling API calls



## Architecture

The app uses MVVM [Model-View-ViewModel] architecture to have a unidirectional flow of data, separation of concern, testability, and a lot more.

![Architecture](https://developer.android.com/topic/libraries/architecture/images/final-architecture.png)
