package com.example.demo1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.demo1.adapter.ProductListAdapter;
import com.example.demo1.bean.Product;
import com.example.demo1.entity.ProductEntity;
import com.example.demo1.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ProductListActivity extends AppCompatActivity {

    private List<Product> productList = new ArrayList<>();
    private ProductRepository productRepository;
    private ProductListAdapter productListAdapter;
    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        productRepository = new ProductRepository(this);

        RecyclerView recyclerViewProducts = findViewById(R.id.recycker_view_product_list);
        productListAdapter = new ProductListAdapter(productList, this);
        recyclerViewProducts.setAdapter(productListAdapter);
        recyclerViewProducts.setLayoutManager(new LinearLayoutManager(this));

        // Uncomment the method you want to use to populate the product list
         getProductList();
//        getProductFromFile();

        Button btnCreateList = findViewById(R.id.btn_createlist);
        btnCreateList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductListActivity.this, CeateProductActivity.class);
                startActivityForResult(intent,2);
//                finish();
            }
        });
    }

    private void getProductList() {
        List<ProductEntity> productEntities = productRepository.getAllProduct();
        productList.clear();

        for (ProductEntity item: productEntities) {

            Product product = new Product();
            product.setProductId(""+item.getId());
            product.setProductName(item.getName());
            product.setProductPrice(item.getPrice());
            productList.add(product);
        }

    }

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);
        if (reqCode == 2) {
            List<ProductEntity> productEntities = productRepository.getAllProduct();
            productList.clear();

                for (ProductEntity item: productEntities) {

                    Product product = new Product();
                        product.setProductId(""+item.getId());
                        product.setProductName(item.getName());
                        product.setProductPrice(item.getPrice());
                    productList.add(product);
                }

            // Notify the adapter that the data has changed
            productListAdapter.notifyDataSetChanged();
        }
    }

    private void getProductFromFile() {
        Scanner scanner = new Scanner(getResources().openRawResource(R.raw.productlist));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            StringTokenizer stringTokenizer = new StringTokenizer(line, ",");
            Product product = new Product();
            if (stringTokenizer.hasMoreTokens()) {
                product.setProductId(stringTokenizer.nextToken());
            }
            if (stringTokenizer.hasMoreTokens()) {
                product.setProductName(stringTokenizer.nextToken());
            }
            if (stringTokenizer.hasMoreTokens()) {
                try {
                    product.setProductPrice(Float.parseFloat(stringTokenizer.nextToken()));
                } catch (Exception exception) {
                    Log.d(getClass().getSimpleName(), "Parse price error: " + exception.toString());
                }
            }
            productList.add(product);
        }

        // Notify the adapter that the data has changed
        productListAdapter.notifyDataSetChanged();
    }
}
