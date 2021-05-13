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
import javax.inject.Inject


class MainViewModel : ViewModel() {
    var searchWord = ObservableField<String>()

    @Inject
    lateinit var imagesRepository: OutputRepository

    private val images = MutableLiveData<Resource<PixabayData>>()
    private val compositeDisposable = CompositeDisposable()

    init {
        val imageRepoComponent: ImageRepositoryComponent = DaggerImageRepositoryComponent.create()
        imageRepoComponent.inject(this)
    }

    val resetPage: MutableLiveData<Boolean> = MutableLiveData()

    fun fetchImages(input: String, page: Int) {
        if (page == 1)
            resetPage?.postValue(true)

        images.postValue(Resource.loading(null))
        compositeDisposable.add(
            imagesRepository.getData(input, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ imageList ->
                    images.postValue(Resource.success(imageList))
                }, { throwable ->
                    images.postValue(Resource.error("Something Went Wrong", null))
                })
        )

    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun getImages(): LiveData<Resource<PixabayData>> {
        return images
    }


}