package ru.geekbrains.chatapplication.di;

import javax.inject.Singleton;

import dagger.Component;
import ru.geekbrains.chatapplication.domain.ChatsRepository;
import ru.geekbrains.chatapplication.domain.RoomRepository;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    ChatsRepository getChatsRepository();
}
