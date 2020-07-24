package com.accenture.magicapp.model.data.remote

import com.accenture.magicapp.model.data.pojo.card.Card
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MagicApi {

    @GET("cards")
    fun getAllCards(
        @Query("pageSize") pageSize: Int,
        @Query("page") page: Int
    ): Observable<List<Card>>

    @GET("cards")
    fun getAllCardsBySet(
        @Query("set") setCode: String,
        @Query("pageSize") pageSize: Int,
        @Query("page") page: Int
    ): Observable<List<Card>>


}