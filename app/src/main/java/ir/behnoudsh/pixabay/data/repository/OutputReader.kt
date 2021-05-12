package ir.behnoudsh.pixabay.data.repository


import ir.behnoudsh.pixabay.domain.model.PixabayData
import ir.behnoudsh.pixabay.domain.model.PixabayResponse

interface OutputReader {
    fun getData(searchWord: String, page: Int)
    fun emitError(error: String)
    fun emitData(data: PixabayData)
}