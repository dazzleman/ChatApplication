package ru.geekbrains.chatapplication.domain;

import java.io.Serializable;
import java.util.Objects;

public class ChatItemModel implements Serializable {

    public Integer id;
    public String name;
    public String time;

    public ChatItemModel() {
    }

    public ChatItemModel(Integer id, String name, String time) {
        this.id = id;
        this.name = name;
        this.time = time;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, time);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final ChatItemModel other = (ChatItemModel) obj;
        return Objects.equals(this.id, other.id);
    }
}
