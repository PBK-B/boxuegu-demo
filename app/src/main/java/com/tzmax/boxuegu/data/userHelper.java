package com.tzmax.boxuegu.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.tzmax.boxuegu.data.userContract.*;

import androidx.annotation.Nullable;

public class userHelper extends SQLiteOpenHelper {

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + User.TABLE_NAME
            + " ( " + User._ID + " INTEGER PRIMARY KEY, "
            + User.COLUMN_NAME_NAME + " TEXT, "
            + User.COLUMN_NAME_PASSWORD + " TEXT, "
            + User.COLUMN_NAME_NICKNAME + " TEXT, "
            + User.COLUMN_NAME_SEX + " TEXT, "
            + User.COLUMN_NAME_SIGNATURE + " TEXT, "
            + User.COLUMN_NAME_ENCRYPTED + " TEXT) ";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + User.TABLE_NAME;

    public userHelper(@Nullable Context context) {
        super(context, DBConfig.DATABASE_NAME, null, DBConfig.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 创建数据库
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO: 这里处理数据升级逻辑，目前是删除重建数据表
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
