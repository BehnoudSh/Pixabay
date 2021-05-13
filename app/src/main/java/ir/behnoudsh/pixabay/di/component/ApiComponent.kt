package ir.behnoudsh.pixabay.di.component

import dagger.Component
import ir.behnoudsh.pixabay.data.api.ApiHelper
import ir.behnoudsh.pixabay.di.module.ApiModule
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class])
interface ApiComponent {
    fun inject(apiHelper: ApiHelper)
}
