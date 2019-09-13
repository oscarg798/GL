package com.reyesmagos.gl.dashboard.di

import com.reyesmagos.gl.core.di.CoreComponent
import com.reyesmagos.gl.dashboard.DashboardActivity
import dagger.Component

@Component(modules = [DashboardModule::class], dependencies = [CoreComponent::class])
interface DashboardComponent  {

    fun inject(dashboardActivity: DashboardActivity)
}
