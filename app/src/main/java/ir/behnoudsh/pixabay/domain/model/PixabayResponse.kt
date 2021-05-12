package ir.behnoudsh.pixabay.domain.model

data class PixabayResponse(
    val error: String,
    val pixabayData:PixabayData
)