package ir.behnoudsh.pixabay.ui.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.android.support.DaggerAppCompatActivity
import ir.behnoudsh.pixabay.data.repository.ImagesRepository
import ir.behnoudsh.pixabay.di.component.ApiComponent
import ir.behnoudsh.pixabay.di.component.DaggerImageRepositoryComponent
import ir.behnoudsh.pixabay.di.component.ImageRepositoryComponent
import ir.behnoudsh.pixabay.domain.model.PixabayHitsData
import javax.inject.Inject


class ImagesViewModel : ViewModel() {
    var searchWord = ObservableField<String>()

    @Inject
    lateinit var imagesRepository: ImagesRepository

    init {
        val imageRepoComponent: ImageRepositoryComponent = DaggerImageRepositoryComponent.create()
        imageRepoComponent.inject(this)
    }

    var showLoadingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    var imagesSuccessLiveData = imagesRepository.imagesSuccessLiveData
    var imagesFailureLiveData = imagesRepository.imagesFailureLiveData
    val resetPage: MutableLiveData<Boolean> = MutableLiveData()

    fun getAllImages(input: String, page: Int) {
        if (page == 1)
            resetPage?.postValue(true)
        showLoadingLiveData?.postValue(true)
        imagesRepository.getData(
            input, page
        )
    }


}