package com.reyesmagos.gl.core

import android.app.Application
import com.reyesmagos.gl.core.di.AppModule
import com.reyesmagos.gl.core.di.CoreComponent
import com.reyesmagos.gl.core.di.DaggerCoreComponent

class CoreApplication : Application(), CoreComponentProvider {

    private var coreComponent: CoreComponent? = null

    override fun getCoreComponent(): CoreComponent {
        if (coreComponent == null) {
            coreComponent = DaggerCoreComponent.builder()
                .appModule(AppModule(this))
                .build()
        }

        return coreComponent ?: throw CoreComponentNotFoundException()
    }
}
