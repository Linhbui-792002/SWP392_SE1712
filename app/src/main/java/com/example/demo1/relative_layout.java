package com.example.demo1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class relative_layout extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Button btnBackHome ;
    private EditText edtFirstName;
    private EditText edtLastName;
    private EditText edtPhone;
    private EditText edtAddress;

    private Button btn_send;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relative_layout);

        btnBackHome = findViewById(R.id.btn_back_home);
        edtFirstName = findViewById(R.id.edt_first_name);
        edtLastName = findViewById(R.id.edt_last_name);
        edtPhone = findViewById(R.id.edt_phone);
        edtAddress = findViewById(R.id.edt_address);
        btn_send = findViewById(R.id.btn_send);

//        Intent intent = getIntent();
//        if(intent != null){
//            edtFirstName.setText(intent.getStringExtra("USERNAME"));
//            edtLastName.setText(intent.getStringExtra("PASSWORD"));
//        }

        btnBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lastName = edtLastName.getText().toString().trim();
                String firstName = edtFirstName.getText().toString().trim();
                String phone = edtPhone.getText().toString().trim();
                String address = edtAddress.getText().toString().trim();

                Intent intent = new Intent();
                intent.putExtra("USERNAME",firstName);
                intent.putExtra("PASSWORD",lastName);
                setResult(2,intent);
                finish();

                String message =
                        "firstName: " + firstName + "" +
                                "lastName: " + lastName + "" +
                                "phone: " + phone + "" +
                                "address: " + address;
//                Toast.makeText(relative_layout.this, message , Toast.LENGTH_SHORT).show();
//                String url = "https://www.facebook.com/profile.php?id=100086095936556";
//                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phone) );
//                startActivity(intent);


            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences("USERINFO", Context.MODE_PRIVATE);
        String username =  sharedPreferences.getString("USERNAME","");
        edtLastName.setText(edtLastName.getText().toString()+", username:"+ username);
    }




    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}