package com.reyesmagos.gl.dashboard

import com.reyesmagos.gl.core.CoroutineContextProvider
import com.reyesmagos.gl.core.Interactor
import com.reyesmagos.gl.core.entities.Post
import com.reyesmagos.gl.core.entities.ViewPost
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext

class DashboardActivityPresenter(
    private val getPostInteractor: Interactor<List<Post>, Unit>,
    override val coroutinesContextProvider: CoroutineContextProvider
) : DashboardContract.Presenter {

    override var view: DashboardContract.View? = null
    override val parentJob: Job = Job()

    override fun onViewReady() {
        launchJobOnMainDispatchers {
            try {
                val viewPosts = withContext(coroutinesContextProvider.backgroundContext) {
                    getPostInteractor(Unit).map {
                        ViewPost(
                            it.id,
                            it.firstName,
                            it.lastName,
                            it.postBody,
                            it.date,
                            it.imageUrl
                        )
                    }
                }

                view?.showPosts(viewPosts)
            } catch (t: Throwable) {
                if (t.message != null) {
                    view?.showError(t.message!!)
                } else {
                    view?.showError(R.string.general_error)
                }
            }
        }
    }

    override fun onTvPostClicked() {
        view?.showAddPostActivity()
    }

    override fun onPostAdded(viewPost: ViewPost) {
        view?.addPost(viewPost)
    }
}
