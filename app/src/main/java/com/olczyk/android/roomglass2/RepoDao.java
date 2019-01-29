package com.olczyk.android.roomglass2;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
abstract class RepoDao extends BaseDao<Repo> {

    @Query("SELECT * FROM repo")
    abstract List<Repo> getAllRepos();
}
