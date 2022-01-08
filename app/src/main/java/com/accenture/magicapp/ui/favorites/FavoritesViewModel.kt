package com.accenture.magicapp.ui.favorites

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.accenture.magicapp.data.MagicRepository
import com.accenture.magicapp.domain.model.CardItemEntity
import com.accenture.magicapp.domain.model.CardsItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class FavoritesViewModel(application: Application) : AndroidViewModel(application) {

    private val cardsList = MutableLiveData<List<CardsItem>>()
    private val repository = MagicRepository(getApplication())
    private val disposable = CompositeDisposable()

    fun getCardsList(): LiveData<List<CardsItem>> {
        return this.cardsList
    }

    fun getFavoritesCards() {
        disposable.add(
            repository.getFavoritesCards()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError { throwable: Throwable? ->
                    Log.e(
                        null,
                        null,
                        throwable
                    )
                }
                .subscribe { entities ->
                    cardsList.value = entities.map { mapCardEntityToCardItem(it) }
                }
        )
    }

    private fun mapCardEntityToCardItem(cardItemEntity: CardItemEntity): CardsItem {
        return CardsItem(
            imageUrl = cardItemEntity.imageUrl,
            itemViewIdentify = cardItemEntity.itemViewIdentify
        )
    }



}