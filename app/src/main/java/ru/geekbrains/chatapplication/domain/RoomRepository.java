package ru.geekbrains.chatapplication.domain;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public interface RoomRepository {

    Flowable<List<MessageModel>> getMessages();

    Completable addMessage(MessageModel item);
}
