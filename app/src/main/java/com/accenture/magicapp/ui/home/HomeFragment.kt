package com.accenture.magicapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.accenture.magicapp.R
import com.accenture.magicapp.util.Common
import com.accenture.magicapp.domain.model.CardsItem
import com.accenture.magicapp.ui.screenslide.ScreenSlidePagerActivity

class HomeFragment : Fragment(R.layout.fragment_main_recycler), HomeCardListener {

    private lateinit var homeViewModel: HomeViewModel
    private var fragmentCardList: List<CardsItem> = listOf()
    private val adapter: HomeCardAdapter = HomeCardAdapter(fragmentCardList, this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        initRecyclerView(view)

        homeViewModel.getAllCards()
        observe()
    }

    private fun observe() {
        homeViewModel.cardsList.observe(viewLifecycleOwner, Observer {
            if (it.count() >= 0) {
                adapter.updateList(it)
            }
        })

        homeViewModel.validation.observe(viewLifecycleOwner, Observer {
            if (it.getStatus()) {
                Toast.makeText(context, "mensagem da homeFragment", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, it.getMessage(), Toast.LENGTH_SHORT).show()

            }
        })
    }

    private fun initRecyclerView(root: View) {
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

        return when (adapter.getItemViewType(position)) {
            Common.VIEWTYPE.HEADER_MAIN,
            Common.VIEWTYPE.HEADER_TYPE -> 3
            else -> 1
        }
    }

    override fun cardOnClick(cards: CardsItem) {
        val intent = Intent(context, ScreenSlidePagerActivity::class.java)
        val bundle = Bundle()
        bundle.putParcelable("cards", cards)
        intent.putExtras(bundle)
        startActivity(intent)
    }

}