package com.example.demo1.repository;

import android.content.Context;

import com.example.demo1.dao.ProductDAO;
import com.example.demo1.dao.ProductRoomDatabase;
import com.example.demo1.entity.ProductEntity;

import java.util.List;

public class ProductRepository {
    private ProductDAO productDAO;
    public  ProductRepository(Context context){
        ProductRoomDatabase productRoomDatabase = ProductRoomDatabase.getInstance(context);
        productDAO = productRoomDatabase.productDAO();
    }

    public void createProduct(ProductEntity product){
        productDAO.insert(product);
    }

    public ProductEntity getProduct(int productId){
       return productDAO.select(productId);
    }

    public void updateProduct(ProductEntity productEntity){
        productDAO.update(productEntity);
    }

    public List<ProductEntity> getAllProduct(){
        return productDAO.selectAll();
    }
}
