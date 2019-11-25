package ru.geekbrains.chatapplication.data;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;

import java.lang.ref.Reference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import durdinapps.rxfirebase2.RxFirebaseDatabase;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.functions.Function;
import ru.geekbrains.chatapplication.domain.ChatItemModel;
import ru.geekbrains.chatapplication.domain.ChatsRepository;

public class ChatsRepositoryImpl implements ChatsRepository {

    private DatabaseReference database;

    public ChatsRepositoryImpl(DatabaseReference database) {
        this.database = database;
    }

    @Override
    public Flowable<List<ChatItemModel>> getChats() {
        return RxFirebaseDatabase.observeValueEvent(database, new Function<DataSnapshot, List<ChatItemModel>>() {
            @Override
            public List<ChatItemModel> apply(DataSnapshot dataSnapshot) throws Exception {
                List<ChatItemModel> list = new ArrayList<>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    ChatItemModel user = postSnapshot.getValue(ChatItemModel.class);
                    list.add(user);
                }
                return list;
            }
        });
    }

    @Override
    public Completable addChat(ChatItemModel item) {
        return RxFirebaseDatabase.setValue(database.child(item.name), item);
    }
}
