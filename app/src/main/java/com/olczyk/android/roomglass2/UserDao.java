package com.olczyk.android.roomglass2;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;
import java.util.List;

@Dao
abstract class UserDao extends BaseDao<User> {

    @Query("SELECT * FROM user")
    abstract List<User> getAllUsers();
}
