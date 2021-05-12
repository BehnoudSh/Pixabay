package ir.behnoudsh.pixabay.di.component

import dagger.Component
import ir.behnoudsh.pixabay.data.repository.ImagesRepository
import ir.behnoudsh.pixabay.di.module.ApiModule
import ir.behnoudsh.pixabay.di.module.ViewModelModule
import ir.behnoudsh.pixabay.ui.view.MainActivity
import ir.behnoudsh.pixabay.ui.viewmodel.ImagesViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelModule::class])
interface ViewModelComponent {
    fun inject(mainActivity: MainActivity)
}
