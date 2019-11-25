package ru.geekbrains.chatapplication.presentation.room;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import io.reactivex.android.schedulers.AndroidSchedulers;
import ru.geekbrains.chatapplication.custom.BaseViewModel;
import ru.geekbrains.chatapplication.domain.MessageModel;
import ru.geekbrains.chatapplication.domain.RoomInteractor;

public class RoomViewModel extends BaseViewModel implements LifecycleObserver {

    private RoomInteractor interactor;
    private Integer idRoom;

    public MutableLiveData<List<MessageModel>> messageLiveData = new MutableLiveData<List<MessageModel>>();

    public RoomViewModel(
            RoomInteractor interactor,
            Integer idRoom
    ) {
        this.interactor = interactor;
        this.idRoom = idRoom;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreateLifeCycle() {
        disposable.add(
                interactor.subscribeMessages()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                (m) -> messageLiveData.setValue(m),
                                (t) -> System.out.println(t)
                        )
        );
    }

    void addChat(String text) {
        Integer index = new SecureRandom().nextInt(Integer.MAX_VALUE);
        disposable.add(
                interactor.addMessage(new MessageModel(
                        index,
                        text,
                        new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(new Date())
                ))
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(() -> System.out.println("good"),
                                (t) -> System.out.println(t))
        );
    }
}
