package ir.behnoudsh.pixabay.data.api

import ir.behnoudsh.pixabay.domain.model.PixabayImages
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET(
        "?key=21493154-5e65420f55ac8bf958559dcc3&image_type=photo&pretty=true"
    )
    suspend fun getImages(
        @Query("q") input: String?,
        @Query("page") page: Int
    ): Response<PixabayImages>?

}