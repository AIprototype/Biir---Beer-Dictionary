package com.example.biir.di.module

import com.example.biir.ui.DetailFragment
import com.example.biir.ui.ListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainModule {
    @ContributesAndroidInjector
    internal abstract fun contributeListFragment(): ListFragment

    @ContributesAndroidInjector
    internal abstract fun contributeDetailFragment(): DetailFragment
}