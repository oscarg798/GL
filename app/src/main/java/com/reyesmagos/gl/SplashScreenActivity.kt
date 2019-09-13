package com.reyesmagos.gl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.reyesmagos.gl.core.constants.DASHBOARD_DEEP_LINK
import com.reyesmagos.gl.core.extensions.startDeepLinkIntent
import kotlinx.android.synthetic.main.activity_main.*

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ivIcon?.postDelayed({
            startDeepLinkIntent(DASHBOARD_DEEP_LINK)
        }, TIME_TO_SHOW_SCREEN)
    }

    companion object {
        const val TIME_TO_SHOW_SCREEN = 3000L
    }
}
