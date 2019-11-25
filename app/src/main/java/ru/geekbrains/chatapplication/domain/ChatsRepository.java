package ru.geekbrains.chatapplication.domain;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public interface ChatsRepository {

    Flowable<List<ChatItemModel>> getChats();

    Completable addChat(ChatItemModel item);
}
