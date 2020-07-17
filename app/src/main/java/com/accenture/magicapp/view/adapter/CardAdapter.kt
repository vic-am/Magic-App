package com.accenture.magicapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.accenture.magicapp.R
import com.accenture.magicapp.model.mock.MockCards

class CardAdapter : RecyclerView.Adapter<CardAdapter.ViewHolder>() {

    private var mCardList: List<MockCards> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val item =
            LayoutInflater.from(parent.context).inflate(R.layout.card_recycler_view, parent, false)
        return ViewHolder(item)
    }

    override fun getItemCount(): Int {
        return mCardList.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mCardList[position])
    }

    fun updateList(list: List<MockCards>) {
        mCardList = list
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(card: MockCards) {
            val cardImage = itemView.findViewById<ImageView>(R.id.imageViewCard)
            cardImage.setImageResource(card.image)
        }

    }

}