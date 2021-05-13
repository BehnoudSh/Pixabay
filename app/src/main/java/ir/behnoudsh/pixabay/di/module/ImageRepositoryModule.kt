package ir.behnoudsh.pixabay.di.module

import dagger.Module
import dagger.Provides
import ir.behnoudsh.pixabay.data.repository.ImageRepository
import ir.behnoudsh.pixabay.data.repository.OutputRepository
import javax.inject.Singleton

@Module
class ImageRepositoryModule {

    @Singleton
    @Provides
    fun providesImageRepository(): OutputRepository {
        return ImageRepository()
    }
}