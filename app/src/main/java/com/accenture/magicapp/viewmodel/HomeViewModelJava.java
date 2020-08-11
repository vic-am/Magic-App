package com.accenture.magicapp.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.accenture.magicapp.model.data.MagicRepository;
import com.accenture.magicapp.model.data.pojo.CardsItem;
import com.accenture.magicapp.model.data.pojo.Sets;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class HomeViewModelJava extends AndroidViewModel {


    private MutableLiveData<List<CardsItem>> cardsList = new MutableLiveData<>();

    private MutableLiveData<List<Sets>> setsList = new MutableLiveData<>();
    private List<String> setsNameList = new ArrayList<>();

    private MagicRepository repository = new MagicRepository(getApplication().getApplicationContext());
    private CompositeDisposable disposable = new CompositeDisposable();
    private int setIterator = 1;
    private int maxPageSize = 100;
    private int page = 0;


    public HomeViewModelJava(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<CardsItem>> getCardsList() {
        return this.cardsList;
    }

    public MutableLiveData<List<Sets>> getSetsList() {
        return this.setsList;
    }

    public List<String> getSetsNameList() {
        return setsNameList;
    }


    public void getAllCards() {
        disposable.add(
                repository.getCardsRepository(maxPageSize, page)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(cards -> {
                            cardsList.setValue(cards.getCards());
                        })
        );
    }



    public void getCardsBySet() {
        String code = setsList.getValue().get(setIterator).getCode();
        disposable.add(
                repository.getCardsBySetRepository(code, maxPageSize, page)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnError(throwable -> Log.e(null, null, throwable))
                        .subscribe(cards -> {
                            cardsList.setValue(cards.getCards());
                        })
        );
    }

    public void getAllSets() {
        disposable.add(
                repository.getAllSetsRepository()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnError(throwable -> Log.e(null, null, throwable))
                        .subscribe(sets -> {
                            setsList.setValue(sets.getSets());
                            getCardsBySet();
                        })
        );
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
