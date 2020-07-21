package com.accenture.magicapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.accenture.magicapp.R
import com.accenture.magicapp.model.mock.Common
import com.accenture.magicapp.model.mock.MockCards

class CardAdapter(var mCardList: List<MockCards>) :
    Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            Common.VIEWTYPE.HEADER_MAIN -> {
                HeaderMainViewHolder(
                    layoutInflater.inflate(
                        R.layout.header_main_item,
                        parent,
                        false
                    )
                )
            }

            Common.VIEWTYPE.HEADER_TYPE -> {
                HeaderTypeViewHolder(
                    layoutInflater.inflate(
                        R.layout.header_type_item,
                        parent,
                        false
                    )
                )
            }

            else -> {
                CardViewHolder(
                    layoutInflater.inflate(
                        R.layout.card_recycler_item,
                        parent,
                        false
                    )
                )
            }
        }
    }

    override fun getItemCount(): Int {
        return mCardList.count()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CardViewHolder) {
            val cardViewHolder = holder
            cardViewHolder.bind(mCardList[position])
        } else if (holder is HeaderMainViewHolder) {
            val headerMainViewHolder = holder
            headerMainViewHolder.bind("Header title funcionando!")
        } else if (holder is HeaderTypeViewHolder) {
            val headerTypeViewHolder = holder
            headerTypeViewHolder.bind("Header type funcionando!")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (mCardList[position].type == Common.TESTS.HEADER_TEST) {
            Common.VIEWTYPE.HEADER_MAIN
        } else if (mCardList[position].type == Common.TESTS.TYPE_TEST) {
            Common.VIEWTYPE.HEADER_TYPE
        } else {
            Common.VIEWTYPE.BODY_CARDS
        }
    }

    fun updateList(list: List<MockCards>) {
        mCardList = list
    }



    class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(card: MockCards) {
            val cardImage = itemView.findViewById<ImageView>(R.id.imageViewCard)
            cardImage.setImageResource(card.image)
        }

    }

    class HeaderMainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(headerTitle: String) {
            val title = itemView.findViewById<TextView>(R.id.header_main_title)
            title.setText(headerTitle)
        }


    }

    class HeaderTypeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(headerType: String) {
            val title = itemView.findViewById<TextView>(R.id.header_type_title)
            title.setText(headerType)
        }
    }
}