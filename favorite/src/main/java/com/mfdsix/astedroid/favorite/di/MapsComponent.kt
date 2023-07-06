package com.mfdsix.astedroid.favorite.di

import android.content.Context
import com.mfdsix.astedroid.di.MapsModuleDependencies
import com.mfdsix.astedroid.favorite.favorite.FavoriteActivity
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [MapsModuleDependencies::class])
interface MapsComponent {

    fun inject(activity: FavoriteActivity)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(mapsModuleDependencies: MapsModuleDependencies): Builder
        fun build(): MapsComponent
    }

}