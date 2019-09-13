package com.reyesmagos.gl.dashboard.di

import com.reyesmagos.gl.core.CoroutineContextProvider
import com.reyesmagos.gl.core.Interactor
import com.reyesmagos.gl.core.entities.Post
import com.reyesmagos.gl.dashboard.DashboardActivityPresenter
import com.reyesmagos.gl.dashboard.DashboardContract
import com.reyesmagos.gl.dashboard.interactor.GetPostInteractor
import com.reyesmagos.gl.dashboard.network.GetPostService
import com.reyesmagos.gl.dashboard.repository.PostRepository
import com.reyesmagos.gl.dashboard.repository.PostRepositoryImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class DashboardModule {

    @Provides
    fun provideGetPostService(retrofit: Retrofit): GetPostService {
        return retrofit.create(GetPostService::class.java)
    }

    @Provides
    fun providePostRepository(getPostService: GetPostService): PostRepository {
        return PostRepositoryImpl(getPostService)
    }

    @Provides
    fun provideGetPostInteractor(postRepository: PostRepository): Interactor<List<Post>, Unit> {
        return GetPostInteractor(postRepository)
    }

    @Provides
    fun provideDashBoardActivityPresenter(
        getPostInteractor: Interactor<List<Post>, Unit>,
        coroutineContextProvider: CoroutineContextProvider
    ): DashboardContract.Presenter {
        return DashboardActivityPresenter(getPostInteractor, coroutineContextProvider)
    }
}
