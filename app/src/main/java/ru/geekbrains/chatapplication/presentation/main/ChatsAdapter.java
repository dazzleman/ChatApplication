package ru.geekbrains.chatapplication.presentation.main;

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

public class ChatsAdapter extends ListAdapter<ChatItemModel, ChatsAdapter.ChatsViewHolder> {

    static DiffUtil.ItemCallback<ChatItemModel> DiffCallback = new DiffUtil.ItemCallback<ChatItemModel>() {
        @Override
        public boolean areItemsTheSame(@NonNull ChatItemModel oldItem, @NonNull ChatItemModel newItem) {
            return oldItem.id.equals(newItem.id);
        }

        @Override
        public boolean areContentsTheSame(@NonNull ChatItemModel oldItem, @NonNull ChatItemModel newItem) {
            return oldItem.equals(newItem);
        }
    };

    private OnItemClickListener clickListener;

    public ChatsAdapter(OnItemClickListener clickListener) {
        super(DiffCallback);
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ChatsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ChatsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_chats_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ChatsViewHolder holder, int position) {
        holder.bind(getItem(position), clickListener);
    }

    static class ChatsViewHolder extends RecyclerView.ViewHolder {

        private View view;
        private TextView name;
        private TextView time;

        public ChatsViewHolder(@NonNull View itemView) {
            super(itemView);

            view = itemView;
            name = itemView.findViewById(R.id.name);
            time = itemView.findViewById(R.id.time);
        }

        public void bind(final ChatItemModel item, final OnItemClickListener clickListener) {
            name.setText(item.name);
            time.setText(item.time);
            view.setOnClickListener((v) -> clickListener.onClick(item));
        }
    }

    interface OnItemClickListener {
        void onClick(ChatItemModel model);
    }
}
