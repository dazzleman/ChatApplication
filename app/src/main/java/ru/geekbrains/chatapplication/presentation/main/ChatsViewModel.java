package ru.geekbrains.chatapplication.presentation.main;

import java.security.SecureRandom;
import java.util.List;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import io.reactivex.android.schedulers.AndroidSchedulers;
import ru.geekbrains.chatapplication.custom.BaseViewModel;
import ru.geekbrains.chatapplication.domain.ChatItemModel;
import ru.geekbrains.chatapplication.domain.ChatsInteractor;

public class ChatsViewModel extends BaseViewModel implements LifecycleObserver {

    private ChatsInteractor interactor;

    public MutableLiveData<List<ChatItemModel>> chatLiveData = new MutableLiveData<List<ChatItemModel>>();

    public ChatsViewModel(ChatsInteractor interactor) {
        this.interactor = interactor;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreateLifeCycle() {
        disposable.add(
                interactor.subscribeChats()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                (m) -> chatLiveData.setValue(m),
                                (t) -> System.out.println(t)
                        )
        );
    }

    void addChat() {
        Integer index = new SecureRandom().nextInt(Integer.MAX_VALUE);
        disposable.add(
                interactor.addChat(new ChatItemModel(
                        index,
                        "Room " + index,
                        "19.11.2019"
                ))
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(() -> System.out.println("good"),
                                (t) -> System.out.println(t))
        );
    }
}
