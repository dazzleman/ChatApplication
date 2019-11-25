package ru.geekbrains.chatapplication.presentation.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ru.geekbrains.chatapplication.App;
import ru.geekbrains.chatapplication.R;
import ru.geekbrains.chatapplication.domain.ChatItemModel;
import ru.geekbrains.chatapplication.presentation.main.di.DaggerChatsComponent;
import ru.geekbrains.chatapplication.presentation.room.RoomActivity;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import javax.inject.Inject;

public class ChatsActivity extends AppCompatActivity {

    private RecyclerView rvChats;
    private FloatingActionButton add;

    @Inject
    ChatsViewModel viewModel;


    private ChatsAdapter adapter = new ChatsAdapter((item) -> {
        RoomActivity.newInstance(this, item.id);
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaggerChatsComponent.factory()
                .create(
                        this,
                        ((App) getApplication()).getAppComponent()
                )
                .inject(this);

        getLifecycle().addObserver(viewModel);

        initRecycleView();
        initFab();
        subscribe();
    }

    private void subscribe() {
        viewModel.chatLiveData.observe(this, new Observer<List<ChatItemModel>>() {
            @Override
            public void onChanged(List<ChatItemModel> items) {
                adapter.submitList(items);
            }
        });
    }

    private void initFab() {
        add = findViewById(R.id.add);
        add.setOnClickListener((v) -> viewModel.addChat());
    }

    private void initRecycleView() {
        rvChats = findViewById(R.id.rvChats);
        rvChats.setAdapter(adapter);
        rvChats.setLayoutManager(new LinearLayoutManager(this));
        rvChats.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }
}
