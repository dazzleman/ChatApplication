package ru.geekbrains.chatapplication.presentation.room.di;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import dagger.BindsInstance;
import dagger.Component;
import ru.geekbrains.chatapplication.di.ActivityScope;
import ru.geekbrains.chatapplication.di.AppComponent;
import ru.geekbrains.chatapplication.presentation.main.ChatsActivity;
import ru.geekbrains.chatapplication.presentation.main.di.ChatsModule;
import ru.geekbrains.chatapplication.presentation.room.RoomActivity;

@ActivityScope
@Component(modules = RoomModule.class, dependencies = AppComponent.class)
public interface RoomComponent {

    @Component.Factory
    interface Builder {
        RoomComponent create(
                @BindsInstance AppCompatActivity activity,
                @BindsInstance Integer idRoom,
                AppComponent deps
        );
    }

    void inject(RoomActivity activity);
}
