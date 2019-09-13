package com.reyesmagos.gl.createpost.di

import com.reyesmagos.gl.core.CoroutineContextProvider
import com.reyesmagos.gl.createpost.CreatePostActivityPresenter
import com.reyesmagos.gl.createpost.CreatePostContract
import dagger.Module
import dagger.Provides

@Module
class CreatePostModule {

    @Provides
    fun provideCreatePostActivityPresenter(coroutineContextProvider: CoroutineContextProvider): CreatePostContract.Presenter {
        return CreatePostActivityPresenter(coroutineContextProvider)
    }
}
