package com.reyesmagos.gl.createpost

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.airbnb.deeplinkdispatch.DeepLink
import com.google.android.material.snackbar.Snackbar
import com.reyesmagos.gl.core.CoreComponentProvider
import com.reyesmagos.gl.core.constants.ARGUMENT_POST
import com.reyesmagos.gl.core.constants.CREATE_POST_DEEP_LINK
import com.reyesmagos.gl.core.entities.ViewPost
import com.reyesmagos.gl.core.extensions.toPostDateFormat
import com.reyesmagos.gl.createpost.di.CreatePostModule
import com.reyesmagos.gl.createpost.di.DaggerCreatePostComponent
import kotlinx.android.synthetic.main.activity_create_post.*
import java.util.*
import javax.inject.Inject

@DeepLink(CREATE_POST_DEEP_LINK)
class CreatePostActivity : AppCompatActivity(), CreatePostContract.View {

    @Inject
    lateinit var presenter: CreatePostContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_post)

        DaggerCreatePostComponent.builder()
            .coreComponent((application as CoreComponentProvider).getCoreComponent())
            .createPostModule(CreatePostModule())
            .build()
            .inject(this)

        presenter.bind(this)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.title)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menu?.apply {
            menuInflater.inflate(R.menu.create_post_menu, menu)
        }

        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.item_share) {
            if (etPost?.text?.isEmpty() == true) {
                etPost?.error = getString(R.string.empty_body_error)
                return true
            }

            presenter.onSharePressed(
                ViewPost(
                    102,
                    "Jane",
                    "Doe",
                    etPost!!.text!!.toString(),
                    Calendar.getInstance().toPostDateFormat(),
                    null
                )
            )
        } else if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }

        return false
    }

    override fun onPostSaved(post: ViewPost) {
        setResult(Activity.RESULT_OK, Intent().apply {
            putExtra(ARGUMENT_POST, post)
        })
        finish()
    }
}
