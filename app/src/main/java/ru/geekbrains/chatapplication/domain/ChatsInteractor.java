package ru.geekbrains.chatapplication.domain;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public class ChatsInteractor {

    private ChatsRepository chatsRepository;

    @Inject
    public ChatsInteractor(ChatsRepository chatsRepository) {
        this.chatsRepository = chatsRepository;
    }

    public Flowable<List<ChatItemModel>> subscribeChats() {
        return chatsRepository.getChats();
    }

    public Completable addChat(ChatItemModel item) {
        return chatsRepository.addChat(item);
    }
}
