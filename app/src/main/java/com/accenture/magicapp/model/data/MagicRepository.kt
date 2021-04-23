package com.accenture.magicapp.model.data

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.accenture.magicapp.R
import com.accenture.magicapp.model.data.entities.CardItemEntity
import com.accenture.magicapp.model.data.local.CardDatabase
import com.accenture.magicapp.model.data.pojo.CardResponse
import com.accenture.magicapp.model.data.pojo.SetResponse
import com.accenture.magicapp.model.data.remote.ApiListener
import com.accenture.magicapp.model.data.remote.MagicApi
import com.accenture.magicapp.model.data.remote.RetrofitService
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MagicRepository(val context: Context) {

    //Retrofit
    private val remote = RetrofitService.createService(MagicApi::class.java)

    //Room
    private val cardDatabase = CardDatabase.getDatabase(context).cardDAO()

    //Métodos Retrofit
    fun getCardsRepository(pageSize: Int = 20, page: Int = 0, listener: ApiListener<CardResponse>) {
        val call: Call<CardResponse> = remote.getAllCards(pageSize, page)
        cardResponse(call, listener)
    }

    fun getAllSetsRepository(listener: ApiListener<SetResponse>) {
        val call: Call<SetResponse> = remote.getAllSets()
        setResponse(call, listener)
    }

    fun getCardsBySetRepository(
        setCode: String,
        pageSize: Int = 10,
        page: Int = 0,
        listener: ApiListener<CardResponse>
    ) {
        val call: Call<CardResponse> = remote.getAllCardsBySet(setCode, pageSize, page)
        cardResponse(call, listener)
    }

    private fun cardResponse(call: Call<CardResponse>, listener: ApiListener<CardResponse>) {
        if (!isConnectionAvailable(context)) {
            listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        }

        call.enqueue(object : Callback<CardResponse> {
            override fun onResponse(call: Call<CardResponse>, response: Response<CardResponse>) {
                if (response.code() != 200) {
                    val validation = response.errorBody()?.string()
                    if (validation != null) {
                        listener.onFailure(validation)
                    } else {
                        listener.onFailure(context.getString(R.string.ERROR_UNEXPECTED))
                    }
                } else {
                    response.body()?.let { listener.onSuccess(it) }
                }
            }

            override fun onFailure(call: Call<CardResponse>, t: Throwable) {
                listener.onFailure(context.getString(R.string.ERROR_UNEXPECTED))
            }

        })
    }

    private fun setResponse(call: Call<SetResponse>, listener: ApiListener<SetResponse>) {
        if (!isConnectionAvailable(context)) {
            listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        }

        call.enqueue(object : Callback<SetResponse> {
            override fun onResponse(call: Call<SetResponse>, response: Response<SetResponse>) {
                if (response.code() != 200) {
                    val validation = response.errorBody()?.string()
                    if (validation != null) {
                        listener.onFailure(validation)
                    } else {
                        listener.onFailure(context.getString(R.string.ERROR_UNEXPECTED))
                    }
                } else {
                    response.body()?.let { listener.onSuccess(it) }
                }
            }

            override fun onFailure(call: Call<SetResponse>, t: Throwable) {
                listener.onFailure(context.getString(R.string.ERROR_UNEXPECTED))
            }

        })
    }

    private fun isConnectionAvailable(context: Context): Boolean {
        var isAvailable = false
        val connectionManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities =
                connectionManager.activeNetwork ?: return false
            val actNw =
                connectionManager.getNetworkCapabilities(networkCapabilities) ?: return false

            isAvailable = when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false

            }
        } else {
            connectionManager.run {
                connectionManager.activeNetworkInfo?.run {
                    isAvailable = when (type) {
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> true
                        ConnectivityManager.TYPE_ETHERNET -> true
                        else -> false

                    }
                }
            }
        }

        return isAvailable
    }

    //Métodos Room
    fun saveCardAsFavorite(card: CardItemEntity) {
        cardDatabase.saveCard(card)
    }

    fun deleteCardAsFavorite(card: CardItemEntity) {
        cardDatabase.removeCard(card)
    }

    fun getFavoritesCards(): Observable<List<CardItemEntity>> {
        return cardDatabase.loadCards()
    }
}