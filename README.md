

# Pixabay
Android Coding Challenge :)

Kotlin language

MVVM architecture, clean and data binding

modulized architecture

Livedata for reactive programming

Rxjava is used in repository and viewmodel.

Dagger for dependency injection and making the layers loosely coupled

Retrofit for network calls

and other components such as androidX.

My approach is as below:

At first I tried to make each component structure, and then wiring all the components to each other till reaching to the View!

1. preparing application requirements and choosing the right architecture
2. adding all components to the gradle
3. packaging the classes and layers
4. preparing Pixabay API and making all the models
5. retrofit call Pixabay interface using Rxjava in Repository:  Repository has the duty of getting data from the api using retrofit, and preparing the data for viewmodel.
6. adding viewmodel: Viewmodel emits the data, and the view is observing the viewmodel.
7. implementing the main logic of application inside viewmodel and repository
8. view completion: Any time a new data is being fetched, view does something such as filling the recyclerview. this concept has been done using livedata.
9. refactoring and adding some comments
10. using view model for saving the state in case of configuration changes and designing the layouts for both portrait and landscape

I have used pagination for better performance and making the list endless.
I used Glide for caching the images.
pixabay api does not send good response for error handling (I mean standard json), so I handled the error type using rxjava throwable.
I tried to make a standard and beautiful app. the functionality is compeleted, I mean user can click on tags too and the search word changes :)

You can find the final apk in the root of the project, in release mode with the volume of 2.3 megabyte.

Thank you :)
