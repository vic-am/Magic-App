package com.accenture.magicapp.ui.screenslide

import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.adapter.FragmentViewHolder
import com.accenture.magicapp.domain.model.CardsItem
import com.squareup.picasso.Picasso

class ScreenSlidePageAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    var cardListViewPager = mutableListOf<CardsItem>()

    override fun getItemCount(): Int {
        return cardListViewPager.size
    }

    override fun onBindViewHolder(
        holder: FragmentViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
    }

    override fun createFragment(position: Int): Fragment = ScreenSlidePageFragment()

    fun loadImage(view: ImageView, card: CardsItem) {
        Picasso.get().load(card.imageUrl).into(view)
    }
}