package ir.behnoudsh.pixabay.di.module

import dagger.Module
import dagger.Provides
import ir.behnoudsh.pixabay.data.repository.MainRepository
import javax.inject.Singleton

@Module
class ImageRepositoryModule {

    @Singleton
    @Provides
    fun providesImageRepository(): MainRepository {
        return MainRepository()
    }
}