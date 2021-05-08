package ir.behnoudsh.pixabay.data.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private const val BASE_URL = "https://pixabay.com/api/"

    val apiinterface: ApiInterface = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(ApiInterface::class.java)
}