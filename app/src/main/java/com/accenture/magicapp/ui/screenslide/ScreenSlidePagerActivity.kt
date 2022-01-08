package com.accenture.magicapp.ui.screenslide

import android.os.Bundle
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import coil.ImageLoader
import coil.request.ImageRequest
import com.accenture.magicapp.R
import com.accenture.magicapp.domain.model.CardsItem

class ScreenSlidePagerActivity : FragmentActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screen_slide)

        viewPager = findViewById(R.id.pager)
        imageView = findViewById(R.id.viewPagerImageView)
        val pagerAdapter = ScreenSlidePageAdapter(this)
        viewPager.adapter = pagerAdapter

        setImageDetail()
    }

    private fun setImageDetail() {
        val card: CardsItem = intent.extras?.get("cards") as CardsItem
        val imageLoader = ImageLoader(applicationContext)
        val request = ImageRequest.Builder(applicationContext)
            .data(card.imageUrl)
            .target(imageView)
            .placeholder(R.drawable.nocard)
            .build()
        imageLoader.enqueue(request)
    }
}