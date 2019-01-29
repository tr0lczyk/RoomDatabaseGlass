package com.olczyk.android.roomglass2;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.os.AsyncTask;

import java.util.Date;

@Database(entities = {User.class, Repo.class, UserRepoJoin.class}, version = 1)
@TypeConverters({DataConverter.class})
abstract class RepoDatabase extends RoomDatabase {

    public static final String DB_NAME = "databaseRepo.db";
    public static volatile RepoDatabase instance;

    static synchronized RepoDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    RepoDatabase.class,
                    DB_NAME)
                    .addCallback(roomCallback)
                    .fallbackToDestructiveMigration()
                    .addMigrations(FROM_ONE_TO_ANOTHER)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(SupportSQLiteDatabase db) {
            super.onCreate(db);
            new DbAsync(instance).execute();
        }
    };

    private static final Migration FROM_ONE_TO_ANOTHER = new Migration(1,2) {
        @Override
        public void migrate(final SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE Repo " +
                    "ADD COLUMN createdAt TEXT");
        }
    };

    private static class DbAsync extends AsyncTask<Void, Void, Void> {
        RepoDao repoDao;
        UserDao userDao;
        UserRepoJoinDao userRepoJoinDao;

        public DbAsync(RepoDatabase repoDatabase) {
            this.repoDao = repoDatabase.getRepoDao();
            this.userDao = repoDatabase.getUserDao();
            this.userRepoJoinDao = repoDatabase.getUserRepoJoinDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            repoDao.insert(new Repo(1, "Dupa",
                    "https://ichef.bbci.co.uk/news/624/cpsprodpb/5D01/production/_95790832_gettyimages-516700094.jpg", new Date()));
            userDao.insert(new User(1, "kotek",
                    "https://ichef.bbci.co.uk/news/624/cpsprodpb/5D01/production/_95790832_gettyimages-516700094.jpg"));
            userRepoJoinDao.insert(new UserRepoJoin(1, 1));
            return null;
        }
    }

    public abstract UserDao getUserDao();
    public abstract RepoDao getRepoDao();
    public abstract UserRepoJoinDao getUserRepoJoinDao();
}
