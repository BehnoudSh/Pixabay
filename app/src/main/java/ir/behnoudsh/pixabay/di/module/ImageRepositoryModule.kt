package ir.behnoudsh.pixabay.di.module

import dagger.Module
import dagger.Provides
import ir.behnoudsh.pixabay.data.repository.ImagesRepository
import javax.inject.Singleton

@Module
class ImageRepositoryModule {

    @Singleton
    @Provides
    fun providesImageRepository(): ImagesRepository {
        return ImagesRepository()
    }
}