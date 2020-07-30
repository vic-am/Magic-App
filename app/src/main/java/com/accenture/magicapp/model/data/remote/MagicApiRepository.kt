package com.accenture.magicapp.model.data.remote

import com.accenture.magicapp.model.data.pojo.CardResponse
import com.accenture.magicapp.model.data.pojo.Sets
import io.reactivex.Observable

class MagicApiRepository {

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
}