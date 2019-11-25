package ru.geekbrains.chatapplication.custom;

import androidx.lifecycle.ViewModel;
import io.reactivex.disposables.CompositeDisposable;

public class BaseViewModel extends ViewModel {

    protected CompositeDisposable disposable = new CompositeDisposable();

    @Override
    protected void onCleared() {
        disposable.clear();
        super.onCleared();
    }
}
