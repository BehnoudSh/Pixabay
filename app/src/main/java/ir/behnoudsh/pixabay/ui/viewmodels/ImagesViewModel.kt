package ir.behnoudsh.pixabay.ui.viewmodels

import android.app.Application
import androidx.databinding.Observable
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ir.behnoudsh.pixabay.data.repository.ImagesRepository
import javax.inject.Inject


class ImagesViewModel : ViewModel(), Observable {
    var searchWord = ObservableField<String>()

    @Inject
    val imagesRepository: ImagesRepository = ImagesRepository()
    var showloadingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val imagesSuccessLiveData = imagesRepository.getImagesSuccessLiveData
    val imagesFailureLiveData = imagesRepository.getImagesFailureLiveData
    val resetPage: MutableLiveData<Boolean> = MutableLiveData()

    fun getAllImages(input: String, page: Int) {
        if (page == 1)
            resetPage?.postValue(true)
        showloadingLiveData?.postValue(true)
        imagesRepository.getImages(
            input, page
        )
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }
}