package com.olczyk.android.roomglass2;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Update;

abstract class BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract void insert(T entity);

    @Update
    abstract void update(T entity);

    @Delete
    abstract void delete(T entity);
}
