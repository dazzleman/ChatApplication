package ru.geekbrains.chatapplication.presentation.room.di;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import dagger.Module;
import dagger.Provides;
import durdinapps.rxfirebase2.RxFirebaseDatabase;
import ru.geekbrains.chatapplication.data.RoomRepositoryImpl;
import ru.geekbrains.chatapplication.di.ActivityScope;
import ru.geekbrains.chatapplication.domain.RoomRepository;
import ru.geekbrains.chatapplication.presentation.main.ChatsViewModel;
import ru.geekbrains.chatapplication.presentation.main.di.ChatsViewModelFactory;
import ru.geekbrains.chatapplication.presentation.room.RoomViewModel;

@Module
public class RoomModule {

    @Provides
    @ActivityScope
    public RoomViewModel provideRoomViewModel(AppCompatActivity activity, RoomViewModelFactory factory) {
        return ViewModelProviders.of(activity, factory).get(RoomViewModel.class);
    }

    @Provides
    @ActivityScope
    public DatabaseReference provideDatabase(Integer idRoom) {
        return FirebaseDatabase.getInstance().getReference().child("messages").child(idRoom.toString());
    }

    @Provides
    @ActivityScope
    public RoomRepository provideRepository(DatabaseReference databaseReference) {
        return new RoomRepositoryImpl(databaseReference);
    }
}
