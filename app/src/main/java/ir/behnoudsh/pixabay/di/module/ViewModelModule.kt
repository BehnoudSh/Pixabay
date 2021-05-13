package ir.behnoudsh.pixabay.di.module

import dagger.Module
import dagger.Provides
import ir.behnoudsh.pixabay.ui.viewmodel.MainViewModel
import javax.inject.Singleton

@Module
class ViewModelModule {

    @Singleton
    @Provides
    fun providesViewModel(): MainViewModel {
        return MainViewModel()
    }
}