package ir.behnoudsh.pixabay.data.repository

import io.reactivex.Single
import ir.behnoudsh.pixabay.data.api.ApiService
import ir.behnoudsh.pixabay.di.component.ApiComponent
import ir.behnoudsh.pixabay.di.component.DaggerApiComponent
import ir.behnoudsh.pixabay.data.model.PixabayData
import javax.inject.Inject

class MainRepository : OutputReader {

    @Inject
    lateinit var retrofit: ApiService

    init {
        val apiComponent: ApiComponent = DaggerApiComponent.create()
        apiComponent.inject(this)
    }

    override fun getData(searchWord: String, page: Int): Single<PixabayData> {

        return retrofit.getImages(searchWord,page)

    }
}