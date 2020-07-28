package com.accenture.magicapp.model.data.remote

import com.accenture.magicapp.model.data.pojo.jsonpojos.Response
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MagicApi {

    @GET("cards")
    fun getAllCards(
        @Query("pageSize") pageSize: Int,
        @Query("page") page: Int
    ): Observable<Response>

    @GET("cards")
    fun getAllCardsBySet(
        @Query("set") setCode: String,
        @Query("pageSize") pageSize: Int,
        @Query("page") page: Int
    ): Observable<Response>
}