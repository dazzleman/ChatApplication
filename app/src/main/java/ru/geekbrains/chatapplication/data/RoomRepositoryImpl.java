package ru.geekbrains.chatapplication.data;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

import durdinapps.rxfirebase2.RxFirebaseDatabase;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import ru.geekbrains.chatapplication.domain.ChatItemModel;
import ru.geekbrains.chatapplication.domain.ChatsRepository;
import ru.geekbrains.chatapplication.domain.MessageModel;
import ru.geekbrains.chatapplication.domain.RoomRepository;

public class RoomRepositoryImpl implements RoomRepository {

    private DatabaseReference database;

    public RoomRepositoryImpl(DatabaseReference database) {
        this.database = database;
    }

    @Override
    public Flowable<List<MessageModel>> getMessages() {
        return RxFirebaseDatabase.observeValueEvent(database, new Function<DataSnapshot, List<MessageModel>>() {
            @Override
            public List<MessageModel> apply(DataSnapshot dataSnapshot) throws Exception {
                List<MessageModel> list = new ArrayList<>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    MessageModel message = postSnapshot.getValue(MessageModel.class);
                    list.add(message);
                }
                return list;
            }
        });
    }

    @Override
    public Completable addMessage(MessageModel item) {
        return RxFirebaseDatabase.setValue(database.child(item.id.toString()), item);
    }
}
