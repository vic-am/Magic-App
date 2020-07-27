package com.accenture.magicapp.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.accenture.magicapp.R
import com.accenture.magicapp.model.mock.Common
import com.accenture.magicapp.model.mock.MockCards
import com.accenture.magicapp.view.`interface`.CardListener
import com.accenture.magicapp.view.activity.ScreenSlidePagerActivity
import com.accenture.magicapp.view.adapter.CardAdapter
import com.accenture.magicapp.viewmodel.HomeViewModel

class HomeFragment : Fragment(R.layout.fragment_main_recycler), CardListener {

    private lateinit var homeViewModel: HomeViewModel
    private var cardList: List<MockCards> = listOf()
    private val mAdapter: CardAdapter = CardAdapter(cardList, this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        initRecyclerView(view)

        homeViewModel.addNewCards()
        homeViewModel.addNewCards()
        homeViewModel.addNewCards()
        homeViewModel.addNewCards()
        homeViewModel.addNewCards()
        homeViewModel.postValue()
        homeViewModel.getCardList().observe(viewLifecycleOwner, Observer {
            mAdapter.updateList(it)
        })
    }

    fun initRecyclerView(root: View) {
        val recyclerView = root.findViewById<RecyclerView>(R.id.recyclerViewHome)
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
        var spanValue =
            when (viewType) {
                Common.VIEWTYPE.HEADER_MAIN,
                Common.VIEWTYPE.HEADER_TYPE -> 3
                else -> 1
            }

        return spanValue
    }

    override fun cardOnClick(card: MockCards) {
        startActivity(Intent(context, ScreenSlidePagerActivity::class.java))
    }
}