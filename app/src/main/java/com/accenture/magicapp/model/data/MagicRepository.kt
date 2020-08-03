package com.accenture.magicapp.model.data

import android.content.Context
import com.accenture.magicapp.model.data.local.CardDatabase
import com.accenture.magicapp.model.data.pojo.CardResponse
import com.accenture.magicapp.model.data.pojo.CardsItem
import com.accenture.magicapp.model.data.pojo.Sets
import com.accenture.magicapp.model.data.remote.RetrofitService
import io.reactivex.Observable

class MagicRepository(context: Context) {

    private val cardDatabase = CardDatabase.getDatabase(context).CardDao()

    fun getCardsRepository(pageSize: Int = 10, page: Int = 0): Observable<CardResponse>? {
        return RetrofitService().getApiService()?.getAllCards(pageSize, page)
    }

    fun getAllSetsRepository(): Observable<Sets>? {
        return RetrofitService().getApiService()?.getAllSets()
    }

    fun getCardsBySetRepository(
        setCode: String,
        pageSize: Int = 10,
        page: Int = 0
    ): Observable<CardResponse>? {
        return RetrofitService().getApiService()?.getAllCardsBySet(setCode, pageSize, page)
    }

    fun saveCardAsFavorite(card: CardsItem) {
        cardDatabase.saveCard(card)
    }

    fun deleteCardAsFavorite(card: CardsItem) {
        cardDatabase.removeCard(card)
    }


    fun getFavoritesCards() {
        cardDatabase.loadCards()
    }
}