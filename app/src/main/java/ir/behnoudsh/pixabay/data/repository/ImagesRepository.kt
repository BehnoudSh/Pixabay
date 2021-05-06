package ir.behnoudsh.pixabay.data.repository

import androidx.lifecycle.MutableLiveData
import ir.behnoudsh.pixabay.data.api.ApiClient
import ir.behnoudsh.pixabay.domain.model.PixabayImageItem

class ImagesRepository {

    val getImagesSuccessLiveData = MutableLiveData<ArrayList<PixabayImageItem>>()
    val getImagesFailureLiveData = MutableLiveData<Boolean>()

    suspend fun getImages(input: String, page: Int) {
        try {
            val response = ApiClient.apiinterface.getImages(input, page)
            if (response?.body() != null) {
                getImagesSuccessLiveData.postValue(response.body()!!.hits)
            } else {
                getImagesFailureLiveData.postValue(true)
            }

        } catch (ex: Exception) {
            var s: String = ex.message.toString();

            getImagesFailureLiveData.postValue(true)

        }
    }

}