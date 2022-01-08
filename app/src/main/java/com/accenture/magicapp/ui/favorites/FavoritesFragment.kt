package com.accenture.magicapp.ui.favorites

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.accenture.magicapp.R
import com.accenture.magicapp.util.Common
import com.accenture.magicapp.domain.model.CardsItem
import com.accenture.magicapp.ui.screenslide.ScreenSlidePagerActivity
import com.accenture.magicapp.ui.home.HomeCardAdapter
import com.accenture.magicapp.ui.home.HomeCardListener


class FavoritesFragment : Fragment(R.layout.fragment_favorites_recycler), HomeCardListener {

    private lateinit var favoritesViewModel: FavoritesViewModel
    private var cardList: List<CardsItem> = listOf()
    private val adapter: HomeCardAdapter = HomeCardAdapter(cardList, this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initRecyclerView(view)
        favoritesViewModel =
            ViewModelProviders.of(this).get(FavoritesViewModel::class.java)

        favoritesViewModel.getFavoritesCards()
        favoritesViewModel.getCardsList().observe(
            viewLifecycleOwner,
            Observer { adapter.updateList(it) }
        )

    }

    fun initRecyclerView(root: View) {
        val recyclerView = root.findViewById<RecyclerView>(R.id.recyclerViewFavorites)
        val layoutManager = GridLayoutManager(context, 3)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return getSpanSizeFromPosition(position)
            }

        }

    }

    fun getSpanSizeFromPosition(position: Int): Int {
        var viewType = adapter.getItemViewType(position)
        var spanValue =
            when (viewType) {
                Common.VIEWTYPE.HEADER_MAIN,
                Common.VIEWTYPE.HEADER_TYPE -> 3
                else -> 1
            }

        return spanValue
    }

    override fun cardOnClick(cards: CardsItem) {
        startActivity(Intent(context, ScreenSlidePagerActivity::class.java))
    }
}