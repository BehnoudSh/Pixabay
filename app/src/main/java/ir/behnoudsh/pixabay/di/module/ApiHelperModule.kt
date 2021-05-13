package ir.behnoudsh.pixabay.di.module

import dagger.Module
import dagger.Provides
import ir.behnoudsh.pixabay.data.api.ApiHelper
import javax.inject.Singleton

@Module
class ApiHelperModule {

    @Singleton
    @Provides
    fun providesApiHelper(): ApiHelper {
        return ApiHelper()
    }
}