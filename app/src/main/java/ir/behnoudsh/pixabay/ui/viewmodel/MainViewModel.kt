package ir.behnoudsh.pixabay.ui.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ir.behnoudsh.pixabay.data.model.PixabayData
import ir.behnoudsh.pixabay.data.repository.OutputRepository
import ir.behnoudsh.pixabay.di.component.DaggerImageRepositoryComponent
import ir.behnoudsh.pixabay.di.component.ImageRepositoryComponent
import ir.behnoudsh.pixabay.utils.Resource
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject


class MainViewModel : ViewModel() {
    var searchWord = ObservableField<String>()

    var page: Int = 1

    @Inject
    lateinit var imagesRepository: OutputRepository

    private val images = MutableLiveData<Resource<PixabayData>>()
    private val compositeDisposable = CompositeDisposable()


    private val resetPage = MutableLiveData<Boolean>()

    private val emptyList = MutableLiveData<Boolean>()


    init {
        val imageRepoComponent: ImageRepositoryComponent = DaggerImageRepositoryComponent.create()
        imageRepoComponent.inject(this)
        searchWord.set("fruits")
    }


    fun fetchImages(input: String, newWord: Boolean) {
        if (newWord) {
            page = 1
            resetPage.postValue(true)
        }

        images.postValue(Resource.loading(null))
        compositeDisposable.add(
            imagesRepository.getData(input, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ imageList ->
                    images.postValue(Resource.success(imageList))
                    if (page == 1 && imageList.hits.size == 0) {
                        emptyList.postValue(true)
                    } else {
                        emptyList.postValue(false)
                    }
                }, { throwable ->
                    var message = ""
                    message =
                        if (throwable is UnknownHostException || throwable is ConnectException)
                            "check your internet connection and try again!"
                        else
                            "something went wrong. try again!"

                    images.postValue(
                        Resource.error(
                            message,
                            null
                        )
                    )
                })
        )

    }


    fun loadMore(input: String) {
        page++
        fetchImages(input, false)
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun getImages(): LiveData<Resource<PixabayData>> {
        return images
    }

    fun getPageStatus(): LiveData<Boolean> {

        return resetPage
    }

    fun getListStatus(): LiveData<Boolean> {

        return emptyList
    }
}