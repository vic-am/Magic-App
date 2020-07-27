package com.accenture.magicapp.model.data.remote

import com.accenture.magicapp.model.data.pojo.card.Card
import io.reactivex.Observable

class MagicApiRepository {

    fun getCardsRepository(pageSize: Int = 10, page: Int = 0): Observable<List<Card>>? {
        return RetrofitService().getApiService()?.getAllCards(pageSize, page)
    }

    fun getCardsBySetRepository(
        setCode: String,
        pageSize: Int = 10,
        page: Int = 0
    ): Observable<List<Card>>? {
        return RetrofitService().getApiService()?.getAllCardsBySet(setCode, pageSize, page)
    }
}