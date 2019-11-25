package ru.geekbrains.chatapplication.domain;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public class RoomInteractor {

    private RoomRepository roomRepository;

    @Inject
    public RoomInteractor(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }


    public Flowable<List<MessageModel>> subscribeMessages() {
        return roomRepository.getMessages();
    }

    public Completable addMessage(MessageModel item) {
        return roomRepository.addMessage(item);
    }
}
