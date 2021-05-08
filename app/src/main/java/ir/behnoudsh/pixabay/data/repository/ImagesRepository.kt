package ir.behnoudsh.pixabay.data.repository

import androidx.lifecycle.MutableLiveData
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.Schedulers.io
import ir.behnoudsh.pixabay.data.api.ApiClient
import ir.behnoudsh.pixabay.domain.model.PixabayImageItem

class ImagesRepository {

    val getImagesSuccessLiveData = MutableLiveData<ArrayList<PixabayImageItem>>()
    val getImagesFailureLiveData = MutableLiveData<Boolean>()
    fun getImages(input: String, page: Int) {
        try {
            val response = ApiClient.apiinterface.getImages(input, page)
            response.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        getImagesSuccessLiveData.postValue(it.hits)
                    },
                    {
                        getImagesFailureLiveData.postValue(true)
                    },
                    {
                    }
                )

        } catch (ex: Exception) {
            getImagesFailureLiveData.postValue(true)
        }
    }
}