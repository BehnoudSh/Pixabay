package ir.behnoudsh.pixabay.data.api

import io.reactivex.Observable
import ir.behnoudsh.pixabay.BuildConfig
import ir.behnoudsh.pixabay.domain.model.PixabayData
import ir.behnoudsh.pixabay.domain.model.PixabayResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET(
        "?key=" + BuildConfig.API_CREDENTIAL + "&image_type=photo&pretty=true"
    )
    fun getImages(
        @Query("q") input: String?,
        @Query("page") page: Int
    ): Observable<PixabayData>

}