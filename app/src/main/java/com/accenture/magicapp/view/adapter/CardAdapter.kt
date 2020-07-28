package com.accenture.magicapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.accenture.magicapp.R
import com.accenture.magicapp.Util.Common
import com.accenture.magicapp.model.data.pojo.CardsItem
import com.accenture.magicapp.view.`interface`.CardListener
import com.squareup.picasso.Picasso

class CardAdapter(internal var cardList: List<CardsItem>, val cardListener: CardListener) :
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
        return cardList.count()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CardViewHolder) {
            val cardViewHolder = holder
            cardViewHolder.bind(cardList[position])
        } else if (holder is HeaderMainViewHolder) {
            val headerMainViewHolder = holder
            headerMainViewHolder.bind("Header title funcionando!")
        } else if (holder is HeaderTypeViewHolder) {
            val headerTypeViewHolder = holder
            headerTypeViewHolder.bind("Header type funcionando!")
        }

        holder.itemView.setOnClickListener {
            if (holder is CardViewHolder) {
                cardListener.cardOnClick(cardList[position])
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (cardList[position].type == Common.TESTS.HEADER_TEST) {
            Common.VIEWTYPE.HEADER_MAIN
        } else if (cardList[position].type == Common.TESTS.TYPE_TEST) {
            Common.VIEWTYPE.HEADER_TYPE
        } else {
            Common.VIEWTYPE.BODY_CARDS
        }
    }

    fun updateList(list: List<CardsItem>) {
        cardList = list
        notifyItemRangeInserted(0, list.size)
    }


    class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(cards: CardsItem) {
            val cardImage = itemView.findViewById<ImageView>(R.id.imageViewCard)
            if (cards.imageUrl == null) {
                cardImage.setImageResource(R.drawable.nocard)
            } else {
                Picasso.get().load(cards.imageUrl).into(cardImage)
            }
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