package com.olczyk.android.roomglass2;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;


@Entity
public class User {

    @PrimaryKey
    public final int id;
    public final String login;
    public final String avatarUrl;

    public User(int id, String login, String avatarUrl) {
        this.id = id;
        this.login = login;
        this.avatarUrl = avatarUrl;
    }
}
