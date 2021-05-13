package ir.behnoudsh.pixabay.data.api

import ir.behnoudsh.pixabay.di.component.ApiComponent
import ir.behnoudsh.pixabay.di.component.DaggerApiComponent
import javax.inject.Inject

class ApiHelper {

    @Inject
    lateinit var apiService: ApiService

    init {
        val apiComponent: ApiComponent = DaggerApiComponent.create()
        apiComponent.inject(this)
    }

    fun getImages(input: String, page: Int) = apiService.getImages(input, page)

}