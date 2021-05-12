package ir.behnoudsh.pixabay.di.component

import dagger.Component
import ir.behnoudsh.pixabay.data.repository.ImagesRepository
import ir.behnoudsh.pixabay.di.module.ApiModule
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class])
interface ApiComponent {
    fun inject(imagesRepository: ImagesRepository)
}
