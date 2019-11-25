package ru.geekbrains.chatapplication.presentation.room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import ru.geekbrains.chatapplication.R;
import ru.geekbrains.chatapplication.domain.ChatItemModel;
import ru.geekbrains.chatapplication.domain.MessageModel;

public class MessageAdapter extends ListAdapter<MessageModel, MessageAdapter.MessageViewHolder> {

    static DiffUtil.ItemCallback<MessageModel> DiffCallback = new DiffUtil.ItemCallback<MessageModel>() {
        @Override
        public boolean areItemsTheSame(@NonNull MessageModel oldItem, @NonNull MessageModel newItem) {
            return oldItem.id.equals(newItem.id);
        }

        @Override
        public boolean areContentsTheSame(@NonNull MessageModel oldItem, @NonNull MessageModel newItem) {
            return oldItem.equals(newItem);
        }
    };

    public MessageAdapter() {
        super(DiffCallback);
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MessageViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_chats_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    static class MessageViewHolder extends RecyclerView.ViewHolder {

        private View view;
        private TextView name;
        private TextView time;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);

            view = itemView;
            name = itemView.findViewById(R.id.name);
            time = itemView.findViewById(R.id.time);
        }

        public void bind(final MessageModel item) {
            name.setText(item.name);
            time.setText(item.time);
        }
    }
}
