package com.reyesmagos.gl.dashboard

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.deeplinkdispatch.DeepLink
import com.google.android.material.snackbar.Snackbar
import com.reyesmagos.gl.core.CoreComponentProvider
import com.reyesmagos.gl.core.constants.ARGUMENT_POST
import com.reyesmagos.gl.core.constants.CREATE_POST_DEEP_LINK
import com.reyesmagos.gl.core.constants.DASHBOARD_DEEP_LINK
import com.reyesmagos.gl.core.constants.REQUEST_CODE_ADD_POST
import com.reyesmagos.gl.core.entities.ViewPost
import com.reyesmagos.gl.core.extensions.parseToDateFormat
import com.reyesmagos.gl.core.extensions.startDeepLinkIntent
import com.reyesmagos.gl.core.extensions.startDeepLinkIntentForResult
import com.reyesmagos.gl.dashboard.adapter.PostRecyclerViewAdapter
import com.reyesmagos.gl.dashboard.di.DaggerDashboardComponent
import com.reyesmagos.gl.dashboard.di.DashboardModule
import kotlinx.android.synthetic.main.activity_dashboard.*
import java.util.*
import javax.inject.Inject

@DeepLink(DASHBOARD_DEEP_LINK)
class DashboardActivity : AppCompatActivity(), DashboardContract.View {

    @Inject
    lateinit var presenter: DashboardContract.Presenter

    private var errorSnackbar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        DaggerDashboardComponent.builder()
            .coreComponent((application as CoreComponentProvider).getCoreComponent())
            .dashboardModule(DashboardModule())
            .build()
            .inject(this)

        presenter.bind(this)
        presenter.onViewReady()
        setup()
    }

    private fun setup() {
        tvDate?.text = Calendar.getInstance().parseToDateFormat()
        rvPost?.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(false)
            adapter = PostRecyclerViewAdapter()

        }

        tvPost?.setOnClickListener {
            presenter.onTvPostClicked()
        }

    }

    override fun addPost(viewPost: ViewPost) {
        (rvPost?.adapter as? PostRecyclerViewAdapter)?.addAtTop(viewPost)
    }

    override fun showAddPostActivity() {
        startDeepLinkIntentForResult(CREATE_POST_DEEP_LINK, REQUEST_CODE_ADD_POST)
    }

    override fun showPosts(posts: List<ViewPost>) {
        (rvPost?.adapter as? PostRecyclerViewAdapter)?.add(posts)
    }

    override fun showError(error: String) {
        if (errorSnackbar?.isShown == true) {
            errorSnackbar?.dismiss()
        }
        val view = clHeader ?: return

        errorSnackbar = Snackbar.make(view, error, Snackbar.LENGTH_INDEFINITE).setAction(
            getString(R.string.dismiss)
        ) {
            errorSnackbar?.dismiss()
        }

        errorSnackbar?.show()
    }

    override fun showError(errorStringId: Int) {
        showError(getString(errorStringId))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_ADD_POST && resultCode == Activity.RESULT_OK && data != null
            && data.extras != null && data.extras!!.containsKey(ARGUMENT_POST)
        ) {
            presenter.onPostAdded(data.extras!!.getParcelable(ARGUMENT_POST)!!)
        }
    }

}
