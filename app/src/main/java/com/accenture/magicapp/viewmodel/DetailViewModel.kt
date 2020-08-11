package com.accenture.magicapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.accenture.magicapp.model.data.MagicRepository
import com.accenture.magicapp.model.data.entities.CardItemEntity
import com.accenture.magicapp.model.data.pojo.CardsItem

class DetailViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = MagicRepository(getApplication())

    fun saveCardToFavorites(card: CardsItem) {
        repository.saveCardAsFavorite(mapCardItemToCardEntity(card))
    }

    private fun mapCardItemToCardEntity(card: CardsItem): CardItemEntity {
        return CardItemEntity(imageUrl = card.imageUrl)
    }

}