package com.olczyk.android.roomglass2;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import java.util.Date;


@Entity
public class Repo {

    @PrimaryKey
    public final int id;
    public final String name;
    public final String url;
    public final Date date;

    public Repo(int id, String name, String url, Date date) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.date = date;
    }
}
