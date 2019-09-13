package com.reyesmagos.gl.deeplink

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.deeplinkdispatch.DeepLinkHandler
import com.reyesmagos.gl.createpost.deeplink.CreatePostDeeplinkModule
import com.reyesmagos.gl.createpost.deeplink.CreatePostDeeplinkModuleLoader
import com.reyesmagos.gl.dashboard.deeplink.DashboardDeepLinkModule
import com.reyesmagos.gl.dashboard.deeplink.DashboardDeepLinkModuleLoader

@DeepLinkHandler(
    DashboardDeepLinkModule::class,
    CreatePostDeeplinkModule::class

)
class DeepLinkHandler : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val deepLinkDelegate = DeepLinkDelegate(
            DashboardDeepLinkModuleLoader(),
            CreatePostDeeplinkModuleLoader()
        )
        deepLinkDelegate.dispatchFrom(this)
        finish()
    }
}
