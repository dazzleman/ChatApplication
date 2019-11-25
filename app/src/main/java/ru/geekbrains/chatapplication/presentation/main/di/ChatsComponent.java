package ru.geekbrains.chatapplication.presentation.main.di;

import javax.inject.Singleton;

import androidx.appcompat.app.AppCompatActivity;
import dagger.BindsInstance;
import dagger.Component;
import ru.geekbrains.chatapplication.di.ActivityScope;
import ru.geekbrains.chatapplication.di.AppComponent;
import ru.geekbrains.chatapplication.di.AppModule;
import ru.geekbrains.chatapplication.presentation.main.ChatsActivity;

@ActivityScope
@Component(modules = ChatsModule.class, dependencies = AppComponent.class)
public interface ChatsComponent {

    @Component.Factory
    interface Builder {
        ChatsComponent create(
                @BindsInstance AppCompatActivity activity,
                AppComponent deps
        );
    }

    void inject(ChatsActivity activity);
}
