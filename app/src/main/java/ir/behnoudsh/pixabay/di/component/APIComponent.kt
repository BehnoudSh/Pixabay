package ir.behnoudsh.pixabay.di.component

import dagger.Component
import ir.behnoudsh.pixabay.data.repository.ImagesRepository
import ir.behnoudsh.pixabay.di.module.ApiClient
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiClient::class])
interface APIComponent {
    fun inject(imagesRepository: ImagesRepository)

}
