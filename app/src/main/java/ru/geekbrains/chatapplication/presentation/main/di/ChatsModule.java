package ru.geekbrains.chatapplication.presentation.main.di;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import dagger.Module;
import dagger.Provides;
import ru.geekbrains.chatapplication.di.ActivityScope;
import ru.geekbrains.chatapplication.presentation.main.ChatsViewModel;

@Module
public class ChatsModule {

    @Provides
    @ActivityScope
    public ChatsViewModel provideChatsViewModel(AppCompatActivity activity, ChatsViewModelFactory factory) {
        return ViewModelProviders.of(activity, factory).get(ChatsViewModel.class);
    }
}
