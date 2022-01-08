package com.accenture.magicapp.ui.screenslide

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.accenture.magicapp.data.MagicRepository
import com.accenture.magicapp.domain.model.CardItemEntity
import com.accenture.magicapp.domain.model.CardsItem

class DetailViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = MagicRepository(getApplication())

    fun saveCardToFavorites(card: CardsItem) {
        repository.saveCardAsFavorite(mapCardItemToCardEntity(card))
    }

    private fun mapCardItemToCardEntity(card: CardsItem): CardItemEntity {
        return CardItemEntity(imageUrl = card.imageUrl)
    }

}