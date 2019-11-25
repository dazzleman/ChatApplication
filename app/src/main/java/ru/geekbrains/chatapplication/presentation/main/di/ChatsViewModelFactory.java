package ru.geekbrains.chatapplication.presentation.main.di;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import ru.geekbrains.chatapplication.domain.ChatsInteractor;
import ru.geekbrains.chatapplication.presentation.main.ChatsViewModel;

public class ChatsViewModelFactory implements ViewModelProvider.Factory {

    private ChatsInteractor interactor;

    @Inject
    public ChatsViewModelFactory(ChatsInteractor interactor) {
        this.interactor = interactor;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ChatsViewModel(interactor);
    }
}
