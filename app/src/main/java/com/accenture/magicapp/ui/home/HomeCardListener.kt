package com.accenture.magicapp.ui.home

import com.accenture.magicapp.domain.model.CardsItem

interface HomeCardListener {

    fun cardOnClick(cards: CardsItem)

}