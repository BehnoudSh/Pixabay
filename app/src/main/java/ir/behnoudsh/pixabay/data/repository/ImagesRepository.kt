package ir.behnoudsh.pixabay.data.repository

import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ir.behnoudsh.pixabay.di.module.ApiClient
import ir.behnoudsh.pixabay.domain.model.PixabayImageItem
import javax.inject.Inject

class ImagesRepository {

    @Inject
    lateinit var retrofit: ApiClient
    val getImagesSuccessLiveData = MutableLiveData<ArrayList<PixabayImageItem>>()
    val getImagesFailureLiveData = MutableLiveData<Boolean>()

    fun getImages(input: String, page: Int) {
        try {
            retrofit = ApiClient
            val response = retrofit.provideRetrofit().getImages(input, page)
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