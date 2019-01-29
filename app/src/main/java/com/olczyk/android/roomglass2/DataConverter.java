package com.olczyk.android.roomglass2;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

public class DataConverter {

    @TypeConverter
    public static Date toDate(long longDate){
        return new Date(longDate);
    }

    @TypeConverter
    public static long fromDate(Date date){
        return date.getTime();
    }
}
