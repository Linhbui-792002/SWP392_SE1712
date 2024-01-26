package com.example.demo1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.demo1.entity.ProductEntity;
import com.example.demo1.repository.ProductRepository;

public class CeateProductActivity extends AppCompatActivity {

    private ProductRepository productRepository;
    private EditText editProductName, editProductPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ceate_product);

        productRepository = new ProductRepository(this);

        Button btnSaveProduct = findViewById(R.id.button_save);
        editProductName = findViewById(R.id.input_product_name);
        editProductPrice = findViewById(R.id.input_product_price);

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

            }
        });
    }
}