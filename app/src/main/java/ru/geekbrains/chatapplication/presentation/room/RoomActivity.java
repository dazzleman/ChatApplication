package ru.geekbrains.chatapplication.presentation.room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.List;

import javax.inject.Inject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ru.geekbrains.chatapplication.App;
import ru.geekbrains.chatapplication.R;
import ru.geekbrains.chatapplication.domain.ChatItemModel;
import ru.geekbrains.chatapplication.domain.MessageModel;
import ru.geekbrains.chatapplication.presentation.main.di.DaggerChatsComponent;
import ru.geekbrains.chatapplication.presentation.room.di.DaggerRoomComponent;

public class RoomActivity extends AppCompatActivity {

    private static String ARG_ID_ROOM = "id_room";

    public static void newInstance(Context context, Integer idRoom) {
        Intent intent = new Intent(context, RoomActivity.class);
        intent.putExtra(ARG_ID_ROOM, idRoom);
        context.startActivity(intent);
    }

    private ImageView sendBtn;
    private EditText edit;
    private RecyclerView rvMessage;

    @Inject
    RoomViewModel viewModel;

    private MessageAdapter adapter = new MessageAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        DaggerRoomComponent.factory()
                .create(
                        this,
                        getIntent().getIntExtra(ARG_ID_ROOM, 0),
                        ((App) getApplication()).getAppComponent()
                )
                .inject(this);

        initViews();
        initRecycleView();
        subscribe();

        getLifecycle().addObserver(viewModel);
    }

    private void initViews() {
        sendBtn = findViewById(R.id.send);
        edit = findViewById(R.id.inputMessage);
        rvMessage = findViewById(R.id.rvMessage);

        sendBtn.setOnClickListener((v) -> {
            viewModel.addChat(edit.getText().toString());
            edit.getText().clear();
        });
    }

    private void initRecycleView() {
        rvMessage.setAdapter(adapter);
        rvMessage.setLayoutManager(new LinearLayoutManager(this));
        rvMessage.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    private void subscribe() {
        viewModel.messageLiveData.observe(this, new Observer<List<MessageModel>>() {
            @Override
            public void onChanged(List<MessageModel> items) {
                adapter.submitList(items);
            }
        });
    }
}
