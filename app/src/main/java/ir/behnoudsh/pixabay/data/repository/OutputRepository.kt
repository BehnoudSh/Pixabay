package ir.behnoudsh.pixabay.data.repository


import io.reactivex.Single
import ir.behnoudsh.pixabay.data.model.PixabayData

interface OutputRepository {
    fun getData(searchWord: String, page: Int): Single<PixabayData>
}