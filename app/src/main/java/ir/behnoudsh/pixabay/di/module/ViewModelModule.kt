package ir.behnoudsh.pixabay.di.module

import dagger.Module
import dagger.Provides
import ir.behnoudsh.pixabay.data.repository.ImagesRepository
import ir.behnoudsh.pixabay.ui.viewmodel.ImagesViewModel
import javax.inject.Singleton

@Module
class ViewModelModule {

    @Singleton
    @Provides
    fun providesViewModel(): ImagesViewModel {
        return ImagesViewModel()
    }
}