package ir.behnoudsh.pixabay.domain.model

data class PixabayImageItem(
    val webformatURL: String?,
    val largeImageURL: String?,
    val user: String?,
    val tags: String?,
    val likes: Integer,
    val favorites: Integer,
    val comments: Integer,
)
