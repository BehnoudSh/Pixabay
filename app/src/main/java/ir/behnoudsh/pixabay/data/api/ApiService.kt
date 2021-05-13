package ir.behnoudsh.pixabay.data.api

import io.reactivex.Observable
import io.reactivex.Single
import ir.behnoudsh.pixabay.BuildConfig
import ir.behnoudsh.pixabay.data.model.PixabayData
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(
        "?key=" + BuildConfig.API_CREDENTIAL + "&image_type=photo&pretty=true"
    )
    fun getImages(
        @Query("q") input: String?,
        @Query("page") page: Int
    ): Single<PixabayData>

}