package com.example.demo1.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.demo1.entity.ProductEntity;

import java.util.List;

@Dao
public interface ProductDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ProductEntity product);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ProductEntity ...productEntities);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void update(ProductEntity product);

    @Query("SELECT * FROM Product_table P WHERE P.Product_ID =:id")
    ProductEntity select(int id);

    @Query("SELECT * FROM Product_table")
    List<ProductEntity> selectAll();
}
