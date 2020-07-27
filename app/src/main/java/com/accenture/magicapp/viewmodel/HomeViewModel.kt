package com.accenture.magicapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.accenture.magicapp.model.data.pojo.card.Card
import com.accenture.magicapp.model.data.remote.MagicApiRepository
import io.reactivex.disposables.CompositeDisposable

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val mutableCardList = MutableLiveData<List<Card>>()
    private val cardList: LiveData<List<Card>> = mutableCardList
    private var disposable = CompositeDisposable()
    val cardRepository = MagicApiRepository()

    fun getCardList(): LiveData<List<Card>> {
        return cardList
    }

    fun getAllCards() {

    }

}