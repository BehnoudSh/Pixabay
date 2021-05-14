
https://user-images.githubusercontent.com/22701499/118270558-79d57c80-b4d5-11eb-8001-0020d43b85d5.mp4

# Pixabay
Android Coding Challenge :)

I have chosen MVVM, clean and databinding for the architecture, which consists of the recommended layers.
Repository has the duty of getting data from the api using retrofit, and preparing the data for viewmodel.
Viewmodel emits the data, and the view is observing the viewmodel.
Any time a new data is being fetched, view does something such as filling the recyclerview. this concept has been done using livedata.
I have used pagination for better performance and making the list endless.
I used Glide for caching the images.
I used dagger for dependency injection.
rxjava is used in repository and viewmodel.
pixabay api does not send good response for error handling (I mean standard json), so I handled the error type using rxjava throwable.
I tried to make a standard and beautiful app. the functionality is compeleted, I mean user can click on tags too and the search word changes :)
Thank you :)
