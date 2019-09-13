package com.reyesmagos.gl.createpost

import com.reyesmagos.gl.core.CoroutineContextProvider
import com.reyesmagos.gl.core.entities.ViewPost
import kotlinx.coroutines.Job

class CreatePostActivityPresenter(
    override val coroutinesContextProvider: CoroutineContextProvider
) : CreatePostContract.Presenter {

    override var view: CreatePostContract.View? = null
    override val parentJob: Job = Job()

    override fun onSharePressed(viewPost: ViewPost) {
        view?.onPostSaved(viewPost)
    }

}
