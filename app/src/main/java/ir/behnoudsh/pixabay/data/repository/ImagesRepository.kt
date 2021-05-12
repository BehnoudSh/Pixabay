package ir.behnoudsh.pixabay.data.repository

import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ir.behnoudsh.pixabay.data.api.ApiInterface
import ir.behnoudsh.pixabay.domain.model.PixabayData
import ir.behnoudsh.pixabay.domain.model.PixabayHitsData
import javax.inject.Inject

class ImagesRepository : OutputReader {

    @Inject
    lateinit var retrofit: ApiInterface

    val imagesSuccessLiveData = MutableLiveData<ArrayList<PixabayHitsData>>()
    val imagesFailureLiveData = MutableLiveData<Boolean>()


    override fun getData(searchWord: String, page: Int) {
        try {
            val response = retrofit.getImages(searchWord, page)
            response.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        emitData(it)
                    },
                    {
                        emitError("")
                    },
                    {
                    }
                )

        } catch (ex: Exception) {
            emitError("")
        }
    }

    override fun emitError(error: String) {
        imagesFailureLiveData.postValue(true);
    }

    override fun emitData(data: PixabayData) {

        imagesSuccessLiveData.postValue(data.hits)

    }


}