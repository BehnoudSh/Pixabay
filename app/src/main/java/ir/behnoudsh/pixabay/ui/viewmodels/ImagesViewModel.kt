package ir.behnoudsh.pixabay.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ir.behnoudsh.pixabay.data.repository.ImagesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ImagesViewModel : ViewModel() {

    var loadingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val imagesRepository: ImagesRepository = ImagesRepository()
    val imagesSuccessLiveData = imagesRepository.getImagesSuccessLiveData
    val imagesFailureLiveData = imagesRepository.getImagesFailureLiveData

    fun getAllImages(input: String, page: Int) {
        loadingLiveData?.postValue(true)
        GlobalScope.launch(Dispatchers.IO) {
            imagesRepository.getImages(
                input, page
            )
        }
    }
}