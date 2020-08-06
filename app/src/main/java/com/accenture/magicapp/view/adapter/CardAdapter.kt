package com.accenture.magicapp.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.accenture.magicapp.R
import com.accenture.magicapp.Util.Common
import com.accenture.magicapp.Util.Common.ITEMVIEWIDENTIFY
import com.accenture.magicapp.Util.Common.VIEWTYPE
import com.accenture.magicapp.model.data.pojo.CardsItem
import com.accenture.magicapp.view.interfaces.CardListener
import com.squareup.picasso.Picasso

class CardAdapter(internal var cardList: List<CardsItem>, val cardListener: CardListener) :
    Adapter<RecyclerView.ViewHolder>() {

    private val organizedCardsList = mutableListOf<CardsItem>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            VIEWTYPE.HEADER_MAIN -> {
                HeaderMainViewHolder(
                    layoutInflater.inflate(
                        R.layout.header_main_item,
                        parent,
                        false
                    )
                )
            }

            VIEWTYPE.HEADER_TYPE -> {
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
            headerMainViewHolder.bind(cardList[position])
        } else if (holder is HeaderTypeViewHolder) {
            val headerTypeViewHolder = holder
            headerTypeViewHolder.bind(cardList[position])
        }

        holder.itemView.setOnClickListener {
            if (holder is CardViewHolder) {
                cardListener.cardOnClick(cardList[position])
            }
        }

        if (holder.adapterPosition == cardList.count()) {
            Log.i("TOAST", " CHEGOU AO FIM")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (cardList[position].itemViewIdentify == ITEMVIEWIDENTIFY.HEADERIDENTIFY) {
            VIEWTYPE.HEADER_MAIN
        } else if (cardList[position].itemViewIdentify == ITEMVIEWIDENTIFY.TYPEIDENTIFY) {
            VIEWTYPE.HEADER_TYPE
        } else {
            VIEWTYPE.BODY_CARDS
        }
    }


    fun updateList(list: List<CardsItem>) {

        val listBySet = list.groupBy { it.set }
        val listByType = list.groupBy { it.types }

        organizeNewList(listBySet)

        cardList = organizedCardsList
        notifyItemRangeInserted(0, organizedCardsList.size)
    }

    private fun newHeaderView(
        list: MutableList<CardsItem>,
        itemViewIdentify: String,
        textToBeDisplayed: String
    ) {
        val headerCard = CardsItem(itemViewIdentify = itemViewIdentify, text = textToBeDisplayed)
        list.add(headerCard)

    }

    private fun organizeNewList(list: Map<String?, List<CardsItem>>) {
        val setsCount = list.keys.size
        for (set in list.keys) {
            if (set != null) {
                newHeaderView(
                        organizedCardsList,
                ITEMVIEWIDENTIFY.HEADERIDENTIFY,
                Common.SETSNAMES.returnSetName(list[set]?.get(0)?.set.toString())
                )

                val listByType = list[set]?.groupBy { it.types }
                if (listByType != null) {
                    for (type in listByType.keys) {
                        val typeName = type.toString().substring(1, type.toString().length - 1)
                        newHeaderView(organizedCardsList, ITEMVIEWIDENTIFY.TYPEIDENTIFY, typeName)

                        for (card in listByType) {
                            if (card.key == type) organizedCardsList.addAll(card.value)
                        }
                    }
                }

            }
        }
    }


    class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(cards: CardsItem) {
            val cardImage = itemView.findViewById<ImageView>(R.id.imageViewCard)
            if (cards.imageUrl == null) {
                cardImage.setImageResource(R.drawable.nocard)
            } else {
                Picasso.get().load(cards.imageUrl)
                    .into(cardImage)
            }
        }

    }

    class HeaderMainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(cards: CardsItem) {
            val title = itemView.findViewById<TextView>(R.id.header_main_title)
            title.setText(cards.text)
        }


    }

    class HeaderTypeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(cards: CardsItem) {
            val title = itemView.findViewById<TextView>(R.id.header_type_title)
            title.setText(cards.text)
        }
    }
}