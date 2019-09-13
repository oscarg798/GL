package com.reyesmagos.gl.createpost.di

import com.reyesmagos.gl.core.di.CoreComponent
import com.reyesmagos.gl.createpost.CreatePostActivity
import dagger.Component

@Component(modules = [CreatePostModule::class], dependencies = [CoreComponent::class])
interface CreatePostComponent {

    fun inject(createPostActivity: CreatePostActivity)
}
