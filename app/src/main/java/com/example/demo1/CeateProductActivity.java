package com.example.demo1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.demo1.bean.Product;
import com.example.demo1.entity.ProductEntity;
import com.example.demo1.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

public class CeateProductActivity extends AppCompatActivity {

    private ProductRepository productRepository;
    private EditText editProductName, editProductPrice;
    private List<Product> productList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ceate_product);
        productRepository = new ProductRepository(this);

        Button btnSaveProduct = findViewById(R.id.button_save);
        editProductName = findViewById(R.id.input_product_name);
        editProductPrice = findViewById(R.id.input_product_price);
        getProduct();

        btnSaveProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String productName = editProductName.getText().toString().trim();
                String productPrice = editProductPrice.getText().toString().trim();
                ProductEntity product = new ProductEntity();
                product.setName(productName);
                product.setPrice(Float.parseFloat(productPrice));
                productRepository.createProduct(product);
                Toast.makeText(CeateProductActivity.this,"create product successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                setResult(2, intent);
                finish();
            }
        });

    }
    public void getProduct() {
        List<ProductEntity> productEntities = productRepository.getAllProduct();
//            productList.clear();

        for (ProductEntity item: productEntities) {

            Product product = new Product();
            product.setProductId(""+item.getId());
            product.setProductName(item.getName());
            product.setProductPrice(item.getPrice());
            productList.add(product);
        }
    }
}
