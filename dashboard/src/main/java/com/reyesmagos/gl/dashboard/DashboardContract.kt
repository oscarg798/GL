package com.reyesmagos.gl.dashboard

import com.reyesmagos.gl.core.BaseContract
import com.reyesmagos.gl.core.StringId
import com.reyesmagos.gl.core.entities.ViewPost

interface DashboardContract {

    interface View : BaseContract.BaseView {

        fun showPosts(posts: List<ViewPost>)

        fun showError(errorStringId: StringId)

        fun showError(error: String)

        fun showAddPostActivity()

        fun addPost(viewPost: ViewPost)

    }

    interface Presenter : BaseContract.BasePresenter<View> {

        fun onViewReady()

        fun onTvPostClicked()

        fun onPostAdded(viewPost: ViewPost)
    }
}
