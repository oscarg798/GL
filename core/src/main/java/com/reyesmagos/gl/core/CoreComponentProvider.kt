package com.reyesmagos.gl.core

import com.reyesmagos.gl.core.di.CoreComponent

interface CoreComponentProvider {

    fun getCoreComponent(): CoreComponent
}
