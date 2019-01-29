package com.olczyk.android.roomglass2;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
abstract class UserRepoJoinDao extends BaseDao<UserRepoJoin> {

    @Query("SELECT * FROM user INNER JOIN user_repo_join ON user.id = user_repo_join.userId WHERE " +
            "user_repo_join.repoId=:repoId")
    abstract List<User> getUsersWithRepo(final int repoId);

    @Query("SELECT * FROM repo INNER JOIN user_repo_join ON repo.id = user_repo_join.repoId WHERE " +
            "user_repo_join.userId=:userId")
    abstract List<Repo> getReposWithUsers(final int userId);
}
