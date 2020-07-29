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

        if (holder.adapterPosition == cardList.count()){
            Log.i("TOAST", " CHEGOU AO FIM")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (cardList[position].itemViewIdentify == Common.TESTS.HEADER_TEST) {
            Common.VIEWTYPE.HEADER_MAIN
        } else if (cardList[position].itemViewIdentify == Common.TESTS.TYPE_TEST) {
            Common.VIEWTYPE.HEADER_TYPE
        } else {
            Common.VIEWTYPE.BODY_CARDS
        }
    }



    fun updateList(list: List<CardsItem>) {
        //Cria uma nova lista para organizar as views
        var newList = mutableListOf<CardsItem>()

        //É criado um card para servir de Header View
        val headerCard = CardsItem()
        headerCard.itemViewIdentify = Common.TESTS.HEADER_TEST
        headerCard.text = list[0].set
        newList.add(headerCard)

        //Aqui é feita uma nova lista dividindo a original em 'types'
        val listByType = list.groupBy { it.types }

        //Esse for vai realizar a reorganização dos cards na 'newList'
        for (type in listByType.keys) {

            //Esse card criado servirá como Type header
            val typeCard = CardsItem()
            typeCard.itemViewIdentify = Common.TESTS.TYPE_TEST

            //Aqui retiramos o primeiro e o último caracter da string. '[' e ']'
            var typeName = type.toString().substring(1, type.toString().length - 1)
            typeCard.text = typeName
            newList.add(typeCard)

            //Aqui é recuperado todos os cards da lista primária e adicionados em ordem de Types
            for (card in list) {
                if (card.types == type) newList.add(card)
            }

        }


        cardList = newList
        notifyItemRangeInserted(0, newList.size)
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