package com.example.demo1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.demo1.adapter.ProductListAdapter;
import com.example.demo1.bean.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ProductListActivity extends AppCompatActivity {

    private List<Product> productList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
//        getProductList();
        getProductFromFile();
        RecyclerView recyclerViewProducts = findViewById(R.id.recycker_view_product_list);
        ProductListAdapter productListAdapter = new ProductListAdapter(productList, this);
        recyclerViewProducts.setAdapter(productListAdapter);
        recyclerViewProducts.setLayoutManager(new LinearLayoutManager(this));


    }
    private void getProductList() {
        Random random = new Random(500);
        for(int i = 0; i< 50; i++){
            Product product = new Product();
            product.setProductId("0"+ i);
            product.setProductName(("Product Name"+ i));
            product.setProductPrice(random.nextFloat());
            productList.add(product);
        }
    }

    private void getProductFromFile(){
        Scanner scanner = new Scanner(getResources().openRawResource(R.raw.productlist));
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            StringTokenizer stringTokenizer = new StringTokenizer(line,",");
            Product product = new Product();
            if(stringTokenizer.hasMoreTokens()){
                product.setProductId(stringTokenizer.nextToken());
            }
            if(stringTokenizer.hasMoreTokens()){
                product.setProductName(stringTokenizer.nextToken());
            }
            if(stringTokenizer.hasMoreTokens()){
               try {
                   product.setProductPrice(Float.parseFloat(stringTokenizer.nextToken()));
               }catch (Exception exception){
                   Log.d(getClass().getSimpleName(),"Parse price is error, "+ exception.toString());
               }
            }
            productList.add(product);
        }
    }
}