package com.accenture.magicapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.accenture.magicapp.R
import com.accenture.magicapp.model.mock.Common
import com.accenture.magicapp.model.mock.MockCards

class FavoritesViewModel(application: Application) : AndroidViewModel(application) {

    private val mutableCardList = MutableLiveData<List<MockCards>>()
    private val cardList: LiveData<List<MockCards>> = mutableCardList
    var newCardList: MutableList<MockCards> = mutableListOf()

    fun getCardList(): LiveData<List<MockCards>> {
        return cardList
    }

    fun addNewCards() {


        newCardList.add(MockCards(R.drawable.cardum, "Card name", Common.TESTS.HEADER_TEST))
        newCardList.add(MockCards(R.drawable.carddois, "Card name", Common.TESTS.TYPE_TEST))
        newCardList.add(MockCards(R.drawable.cardtres, "Card name", "Card Type"))
        newCardList.add(MockCards(R.drawable.carddois, "Card name", "Card Type"))
        newCardList.add(MockCards(R.drawable.cardtres, "Card name", "Card Type"))
        newCardList.add(MockCards(R.drawable.carddois, "Card name", "Card Type"))
        newCardList.add(MockCards(R.drawable.cardtres, "Card name", "Card Type"))


    }

    fun postValue() {
        mutableCardList.postValue(newCardList)
    }

}