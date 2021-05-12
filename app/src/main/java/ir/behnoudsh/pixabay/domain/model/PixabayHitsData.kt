package ir.behnoudsh.pixabay.domain.model

data class PixabayHitsData(
    val webformatURL: String?,
    val largeImageURL: String?,
    val user: String?,
    val tags: String?,
    val likes: Integer,
    val favorites: Integer,
    val comments: Integer,
    val userImageURL: String?
)
