package ru.geekbrains.chatapplication.presentation.room.di;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import ru.geekbrains.chatapplication.domain.ChatsInteractor;
import ru.geekbrains.chatapplication.domain.RoomInteractor;
import ru.geekbrains.chatapplication.presentation.main.ChatsViewModel;
import ru.geekbrains.chatapplication.presentation.room.RoomViewModel;

public class RoomViewModelFactory implements ViewModelProvider.Factory {

    private RoomInteractor interactor;
    private Integer idRoom;

    @Inject
    public RoomViewModelFactory(
            RoomInteractor interactor,
            Integer idRoom
    ) {
        this.interactor = interactor;
        this.idRoom = idRoom;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new RoomViewModel(interactor, idRoom);
    }
}
