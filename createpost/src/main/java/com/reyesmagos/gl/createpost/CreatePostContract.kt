package com.reyesmagos.gl.createpost

import com.reyesmagos.gl.core.BaseContract
import com.reyesmagos.gl.core.StringId
import com.reyesmagos.gl.core.entities.ViewPost

interface CreatePostContract {

    interface View : BaseContract.BaseView {

        fun onPostSaved(post: ViewPost)
    }

    interface Presenter : BaseContract.BasePresenter<View> {

        fun onSharePressed(viewPost: ViewPost)
    }
}
