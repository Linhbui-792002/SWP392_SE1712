package com.example.demo1.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.demo1.bean.UserInfo;

public class DatabaseHandle extends SQLiteOpenHelper {

    private final static  int DB_VERSION = 2;
    private final static String DB_NAME = "UserData";
    private final static String USER_TABLE = "USER_TABLE";
    private final static String USER_NAME = "USER_NAME";
    private final static String PASSWORD = "PASSWORD";
    private final static String ROLE = "ROLE";
    private final static String CAMPUS = "CAMPUS";
    private final static String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS "+ USER_TABLE + "("
            + USER_NAME + " TEXT NOT NULL PRIMARY KEY, "+ PASSWORD+ " TEXT,"
            + ROLE + " TEXT," + CAMPUS + " TEXT)";
    public DatabaseHandle(@Nullable Context context){
        super(context, DB_NAME, null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    if(db.isOpen()){
        db.execSQL(CREATE_USER_TABLE);
    }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    if(db.isOpen()){
        db.execSQL("DROP TABLE " + USER_TABLE);
        db.execSQL(CREATE_USER_TABLE);
    }
    }

    public long insertUser(String username, String password, String role, String campus){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
contentValues.put(USER_NAME,username);
contentValues.put(PASSWORD,password);
contentValues.put(ROLE,role);
contentValues.put(CAMPUS,campus);
       long result =  sqLiteDatabase.insertOrThrow(USER_TABLE,null,contentValues);
        return result;
    }

    public UserInfo getUser(String username){
        UserInfo userInfo = null;

        String selectUser = "SELECT * FROM "+ USER_TABLE +  " WHERE " + USER_NAME + "=?";
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(selectUser, new String[]{username});

        if(cursor.moveToNext()){
            userInfo = new UserInfo();
            int index = cursor.getColumnIndex(PASSWORD);
            userInfo.setPassword(cursor.getString(index));

             index = cursor.getColumnIndex(ROLE);
            userInfo.setPassword(cursor.getString(index));

            index = cursor.getColumnIndex(CAMPUS);
            userInfo.setPassword(cursor.getString(index));
           userInfo.setUsername(username);
        }


        return userInfo;
    }
}
