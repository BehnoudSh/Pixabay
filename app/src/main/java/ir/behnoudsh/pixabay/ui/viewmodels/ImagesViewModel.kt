package ir.behnoudsh.pixabay.ui.viewmodels

import androidx.databinding.Observable
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ir.behnoudsh.pixabay.data.repository.ImagesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject


class ImagesViewModel : ViewModel(), Observable {
    var searchWord = ObservableField<String>()

    @Inject
    val imagesRepository: ImagesRepository = ImagesRepository()
    var loadingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val imagesSuccessLiveData = imagesRepository.getImagesSuccessLiveData
    val imagesFailureLiveData = imagesRepository.getImagesFailureLiveData
    val resetPage: MutableLiveData<Boolean> = MutableLiveData()

    fun getAllImages(input: String, page: Int) {
        if (page == 1)
            resetPage?.postValue(true)
        loadingLiveData?.postValue(true)
        GlobalScope.launch(Dispatchers.IO) {
            imagesRepository.getImages(
                input, page
            )
        }
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }
}