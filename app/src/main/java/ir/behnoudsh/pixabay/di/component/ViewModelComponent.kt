package ir.behnoudsh.pixabay.di.component

import dagger.Component
import ir.behnoudsh.pixabay.di.module.ViewModelModule
import ir.behnoudsh.pixabay.ui.view.MainActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelModule::class])
interface ViewModelComponent {
    fun inject(mainActivity: MainActivity)
}
