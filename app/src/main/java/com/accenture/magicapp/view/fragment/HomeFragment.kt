package com.accenture.magicapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.accenture.magicapp.R
import com.accenture.magicapp.model.mock.Common
import com.accenture.magicapp.model.mock.MockCards
import com.accenture.magicapp.view.adapter.CardAdapter
import com.accenture.magicapp.viewmodel.HomeViewModel

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var cardList: List<MockCards> = listOf()
    private val mAdapter: CardAdapter = CardAdapter(cardList)
    private var spanSizeLookup: GridLayoutManager.SpanSizeLookup? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_main_recycler, container, false)
        initRecyclerView(root)


        homeViewModel.addNewCards()
        homeViewModel.addNewCards()
        homeViewModel.addNewCards()
        homeViewModel.addNewCards()
        homeViewModel.addNewCards()
        homeViewModel.postValue()
        homeViewModel.getCardList().observe(viewLifecycleOwner, Observer {
            mAdapter.updateList(it)
        })

        return root
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
}