package ir.behnoudsh.pixabay.data.repository

import io.reactivex.Single
import ir.behnoudsh.pixabay.data.api.ApiHelper
import ir.behnoudsh.pixabay.data.model.PixabayData
import ir.behnoudsh.pixabay.di.component.ApiHelperComponent
import ir.behnoudsh.pixabay.di.component.DaggerApiHelperComponent
import javax.inject.Inject

class ImageRepository : OutputRepository {

    @Inject
    lateinit var apiHelper: ApiHelper

    init {
        val apiHelperComponent: ApiHelperComponent = DaggerApiHelperComponent.create();
        apiHelperComponent.inject(this)
    }

    override fun getData(searchWord: String, page: Int): Single<PixabayData> {

        return apiHelper.getImages(searchWord, page)

    }
}