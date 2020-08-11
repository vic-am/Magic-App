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
import com.accenture.magicapp.Util.Common
import com.accenture.magicapp.model.data.pojo.CardsItem
import com.accenture.magicapp.model.data.pojo.Sets
import com.accenture.magicapp.view.activity.ScreenSlidePagerActivity
import com.accenture.magicapp.view.adapter.CardAdapter
import com.accenture.magicapp.view.interfaces.CardListener
import com.accenture.magicapp.viewmodel.HomeViewModel
import com.accenture.magicapp.viewmodel.HomeViewModelJava
import kotlinx.android.synthetic.main.fragment_main_recycler.*

class HomeFragment : Fragment(R.layout.fragment_main_recycler), CardListener {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var homeViewModelJava: HomeViewModelJava

    private var fragmentCardList: List<CardsItem> = listOf()

    private val adapter: CardAdapter = CardAdapter(fragmentCardList, this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        homeViewModelJava =
            ViewModelProviders.of(this).get(HomeViewModelJava::class.java)
        initRecyclerView(view)

        loadAllSets()
        loadCardsBySet()


    }

    fun loadAllSets() {
        homeViewModelJava.getAllSets()
    }

    fun loadCardsBySet() {
        homeViewModelJava.cardsList.observe(
            viewLifecycleOwner,
            Observer { adapter.updateList(it) }
        )
    }

    fun initRecyclerView(root: View) {
        val recyclerView = root.findViewById<RecyclerView>(R.id.recyclerViewHome)
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
        val intent = Intent(context, ScreenSlidePagerActivity::class.java)
        var bundle = Bundle()
        bundle.putParcelable("cards", cards)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    private fun setScrollView() {

        recyclerViewHome.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
            }
        })
    }
}