package com.accenture.magicapp.model.data.remote

import com.accenture.magicapp.model.data.pojo.CardResponse
import com.accenture.magicapp.model.data.pojo.SetResponse
import com.accenture.magicapp.model.data.pojo.Sets
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MagicApi {

    @GET("cards")
    fun getAllCards(
        @Query("pageSize") pageSize: Int,
        @Query("page") page: Int
    ): Call<CardResponse>

    @GET("cards")
    fun getAllCardsBySet(
        @Query("set") setCode: String,
        @Query("pageSize") pageSize: Int,
        @Query("page") page: Int
    ): Call<CardResponse>

    @GET("sets")
    fun getAllSets(): Call<SetResponse>

    @GET("sets/{setCode}")
    fun getSetByCode(@Path("setCode") setCode: String): Call<SetResponse>


}