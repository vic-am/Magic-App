package com.accenture.magicapp.view.activity

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.accenture.magicapp.R
import com.accenture.magicapp.model.data.pojo.CardsItem
import com.accenture.magicapp.view.adapter.ScreenSlidePageAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_screen_slide.*

class ScreenSlidePagerActivity : FragmentActivity() {

    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screen_slide)

        viewPager = findViewById(R.id.pager)
        val pagerAdapter = ScreenSlidePageAdapter(this)
        viewPager.adapter = pagerAdapter

        setImageDetail()
    }

    fun setImageDetail() {
        val card: CardsItem = intent.extras?.get("cards") as CardsItem
        Picasso.get().load(card.imageUrl).into(viewPagerImageView)
    }
}