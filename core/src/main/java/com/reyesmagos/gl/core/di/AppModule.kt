package com.reyesmagos.gl.core.di

import android.content.Context
import com.reyesmagos.gl.core.CoroutineContextProvider
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers

@Module
class AppModule(private val contextApplication: Context) {

    @Provides
    fun providerContext(): Context {
        return contextApplication
    }

    @Provides
    fun provideCoroutineContextProvider(): CoroutineContextProvider {
        return CoroutineContextProvider(
            Dispatchers.Main,
            Dispatchers.IO
        )
    }

    @Provides
    fun provideBaseUrl(): String {
        return "http://gl-endpoint.herokuapp.com/"
    }
}
