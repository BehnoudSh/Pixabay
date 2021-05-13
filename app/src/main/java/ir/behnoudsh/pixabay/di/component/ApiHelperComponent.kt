package ir.behnoudsh.pixabay.di.component

import dagger.Component
import ir.behnoudsh.pixabay.data.repository.ImageRepository
import ir.behnoudsh.pixabay.di.module.ApiHelperModule
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiHelperModule::class])
interface ApiHelperComponent {
    fun inject(mainRepository: ImageRepository)
}
