package ir.behnoudsh.pixabay.di.component

import dagger.Component
import ir.behnoudsh.pixabay.di.module.ImageRepositoryModule
import ir.behnoudsh.pixabay.ui.viewmodel.MainViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [ImageRepositoryModule::class])
interface ImageRepositoryComponent {
    fun inject(imagesViewModel: MainViewModel)
}
