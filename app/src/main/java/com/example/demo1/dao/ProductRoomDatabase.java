package com.example.demo1.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.demo1.entity.ProductEntity;

@Database(entities = {ProductEntity.class},version = 1)
public abstract class ProductRoomDatabase  extends RoomDatabase {

    public abstract ProductDAO productDAO();
    private static ProductRoomDatabase INSTANCE = null;

    public static ProductRoomDatabase getInstance(Context context) {
        if(INSTANCE == null){
            synchronized (ProductRoomDatabase.class){
                INSTANCE = Room.databaseBuilder(context, ProductRoomDatabase.class, "ProductDB")
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build();
            }
        }
        return INSTANCE;
    }


}
