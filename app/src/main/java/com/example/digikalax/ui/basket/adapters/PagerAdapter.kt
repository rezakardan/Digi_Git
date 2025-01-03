package com.example.digikalax.ui.basket.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.digikalax.ui.basket.BasketNextShoppingFragment
import com.example.digikalax.ui.basket.BasketShoppingFragment

class PagerAdapter(manager: FragmentManager,lifecycle: Lifecycle):FragmentStateAdapter(manager,lifecycle) {
    override fun getItemCount()=2

    override fun createFragment(position: Int): Fragment {
        return when(position){


            0->BasketShoppingFragment()
            else->BasketNextShoppingFragment()



        }
    }
}