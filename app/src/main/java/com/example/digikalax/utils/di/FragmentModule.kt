package com.example.digikalax.utils.di

import androidx.fragment.app.Fragment
import com.example.digikalax.ui.basket.adapters.PagerAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent


@Module
@InstallIn(FragmentComponent::class)
class FragmentModule {

    @Provides
    fun providePagerAdapter(fragment: Fragment) =
        PagerAdapter(fragment.parentFragmentManager, fragment.lifecycle)


}