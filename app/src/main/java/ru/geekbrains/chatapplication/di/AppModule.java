package ru.geekbrains.chatapplication.di;

import android.content.Context;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.geekbrains.chatapplication.data.ChatsRepositoryImpl;
import ru.geekbrains.chatapplication.domain.ChatsRepository;

@Module
public class AppModule {

    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return context;
    }

    @Provides
    @Singleton
    public DatabaseReference provideDatabaseReference() {
        return FirebaseDatabase.getInstance().getReference().child("chats");
    }

    @Provides
    @Singleton
    public ChatsRepository provideChatRepository(DatabaseReference database) {
        return new ChatsRepositoryImpl(database);
    }
}
