package com.example.demo1.entity;

import androidx.annotation.ColorLong;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "Product_table")
public class ProductEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Product_ID",index = true)
    private int id;

    @ColumnInfo(name = "Product_Name")
    @Nullable
    private String name;

    @ColumnInfo(name = "Price", defaultValue = "0")
    private float price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Nullable
    public String getName() {
        return name;
    }

    public void setName(@Nullable String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
