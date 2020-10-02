package com.tzmax.boxuegu.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

public class userContract {

    public String TAG = "用户数据管理类";

    protected Context mContext;
    protected static userContract mInstance;
    protected userHelper dbHelper;

    public static userContract getInstance(Context mContext) {
        if (mInstance == null) {
            mInstance = new userContract(mContext);
        }
        return mInstance;
    }

    private userContract(Context mContext) {
        this.mContext = mContext;
        dbHelper = new userHelper(mContext);
    }

    // 提供注册用户接口
    public boolean apiSignUp(String name, String password) {
        // TODO: 这里需要添加一个判断账号是否存在的逻辑
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(User.COLUMN_NAME_NAME, name);
        values.put(User.COLUMN_NAME_PASSWORD, password);

        long newRowId = db.insert(User.TABLE_NAME, null, values);

        if (newRowId == -1) {
            return false;
        } else {
            return true;
        }
    }

    // 提供用户登陆接口
    public boolean apiSignIn(String name, String password) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selection = User.COLUMN_NAME_NAME + " = ? AND " + User.COLUMN_NAME_PASSWORD + " = ?";
        String[] selectionArgs = {name, password};

        String sortOrder = User.COLUMN_NAME_NAME + " DESC";

        Cursor cursor = db.query(
                User.TABLE_NAME,   // The table to query
                null,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        List users = new ArrayList<>();
        while (cursor.moveToNext()) {
            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(User._ID));
            users.add(itemId);
        }
        cursor.close();

        if (users.size() < 1) {
            return false;
        } else {
            return true;
        }
    }

    public static class User implements BaseColumns {
        // private String id, name, password, nickname, sex, signature, encrypted;
        //   ID   用户名   密码   昵称   性别   签名   密保

        public static final String TABLE_NAME = "users";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_PASSWORD = "password";
        public static final String COLUMN_NAME_NICKNAME = "nickname";
        public static final String COLUMN_NAME_SEX = "sex";
        public static final String COLUMN_NAME_SIGNATURE = "signature";
        public static final String COLUMN_NAME_ENCRYPTED = "encrypted";
    }

}
