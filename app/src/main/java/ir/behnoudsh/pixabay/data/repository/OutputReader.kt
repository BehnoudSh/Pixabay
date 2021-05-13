package ir.behnoudsh.pixabay.data.repository


import io.reactivex.Single
import ir.behnoudsh.pixabay.data.model.PixabayData

interface OutputReader {
    fun getData(searchWord: String, page: Int): Single<PixabayData>
//    fun emitError(error: String)
//    fun emitData(data: PixabayData)
}