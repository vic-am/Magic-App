package com.accenture.magicapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.accenture.magicapp.model.data.MagicRepository
import com.accenture.magicapp.model.data.pojo.CardResponse
import com.accenture.magicapp.model.data.pojo.CardsItem
import com.accenture.magicapp.model.data.pojo.SetResponse
import com.accenture.magicapp.model.data.pojo.Sets
import com.accenture.magicapp.model.data.remote.ApiListener
import com.accenture.magicapp.model.data.remote.ValidationListener
import io.reactivex.disposables.CompositeDisposable

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val mutableValidation = MutableLiveData<ValidationListener>()
    var validation: LiveData<ValidationListener> = mutableValidation

    private val mutableCardsList = MutableLiveData<List<CardsItem>>()
    val cardsList: LiveData<List<CardsItem>> = mutableCardsList

    private val mutableSetsList = MutableLiveData<List<Sets>>()
    val setsList: LiveData<List<Sets>> = mutableSetsList

    lateinit var setsNameList: MutableList<String>

    private val repository = MagicRepository(application)
    private val disposable = CompositeDisposable()

    private var setIterator = 1
    private var maxPageSize = 100
    private var page = 0

    fun getAllCards() {
        val listener = object : ApiListener<CardResponse> {
            override fun onSuccess(model: CardResponse) {
                mutableCardsList.value = model.cards
            }

            override fun onFailure(string: String) {
                mutableCardsList.value = arrayListOf()
                mutableValidation.value = ValidationListener(string)
            }
        }

        repository.getCardsRepository(listener = listener)
    }

    fun getCardsBySet() {

        val setCode = mutableSetsList.value?.get(setIterator)?.code

        val listener = object : ApiListener<CardResponse> {
            override fun onSuccess(model: CardResponse) {
                mutableCardsList.value = model.cards
            }

            override fun onFailure(string: String) {
                mutableCardsList.value = arrayListOf()
                mutableValidation.value = ValidationListener(string)
            }
        }

        if (setCode != null) {
            repository.getCardsBySetRepository(setCode, listener = listener)
        }
    }

    fun getAllSets() {
        val listener = object : ApiListener<SetResponse> {
            override fun onSuccess(model: SetResponse) {
                mutableSetsList.value = model.sets
            }

            override fun onFailure(string: String) {
                mutableSetsList.value = arrayListOf()
                mutableValidation.value = ValidationListener(string)
            }
        }

        repository.getAllSetsRepository(listener = listener)
    }
}