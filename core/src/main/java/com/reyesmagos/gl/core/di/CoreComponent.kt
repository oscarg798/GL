package com.reyesmagos.gl.core.di

import com.reyesmagos.gl.core.CoroutineContextProvider
import dagger.Component
import retrofit2.Retrofit

@Component(modules = [AppModule::class, NetworkModule::class])
interface CoreComponent{

    fun provideCoroutineContextProvider(): CoroutineContextProvider

    fun provideRetrofit(): Retrofit
}
