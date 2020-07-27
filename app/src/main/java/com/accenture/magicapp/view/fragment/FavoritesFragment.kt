package com.accenture.magicapp.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.accenture.magicapp.R
import com.accenture.magicapp.model.data.pojo.card.Card
import com.accenture.magicapp.model.mock.Common
import com.accenture.magicapp.view.`interface`.CardListener
import com.accenture.magicapp.view.activity.ScreenSlidePagerActivity
import com.accenture.magicapp.view.adapter.CardAdapter
import com.accenture.magicapp.viewmodel.FavoritesViewModel


class FavoritesFragment : Fragment(R.layout.fragment_favorites_recycler), CardListener {

    private lateinit var favoritesViewModel: FavoritesViewModel
    private var cardList: List<Card> = listOf()
    private val mAdapter: CardAdapter = CardAdapter(cardList, this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initRecyclerView(view)
        favoritesViewModel =
            ViewModelProviders.of(this).get(FavoritesViewModel::class.java)


    }

    fun initRecyclerView(root: View) {
        val recyclerView = root.findViewById<RecyclerView>(R.id.recyclerViewFavorites)
        val layoutManager = GridLayoutManager(context, 3)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = mAdapter

        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return getSpanSizeFromPosition(position)
            }

        }

    }

    fun getSpanSizeFromPosition(position: Int): Int {
        var viewType = mAdapter.getItemViewType(position)
        var spanValue = 0

        when (viewType) {
            Common.VIEWTYPE.HEADER_MAIN -> {
                spanValue = 3
            }

            Common.VIEWTYPE.HEADER_TYPE -> {
                spanValue = 3
            }
            else -> spanValue = 1
        }

        return spanValue
    }

    override fun cardOnClick(card: Card) {
        startActivity(Intent(context, ScreenSlidePagerActivity::class.java))
    }
}