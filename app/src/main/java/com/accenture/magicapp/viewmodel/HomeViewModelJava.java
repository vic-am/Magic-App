package com.accenture.magicapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.accenture.magicapp.model.data.pojo.jsonpojos.CardsItem;
import com.accenture.magicapp.model.data.remote.MagicApiRepository;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class HomeViewModelJava extends AndroidViewModel {

    private MutableLiveData<List<CardsItem>> cardsList = new MutableLiveData<>();
    private MagicApiRepository repository = new MagicApiRepository();
    private CompositeDisposable disposable = new CompositeDisposable();

    public HomeViewModelJava(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<CardsItem>> getCardsList() {
        return this.cardsList;
    }

    public void getAllCards() {
        disposable.add(
                repository.getCardsRepository(10, 0)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(cards -> {
                            cardsList.setValue(cards.getCards());
                        })
        );
    }

    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
