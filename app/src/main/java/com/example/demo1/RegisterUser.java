package com.example.demo1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.demo1.database.DatabaseHandle;

public class RegisterUser extends AppCompatActivity {
    private DatabaseHandle databaseHandle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        databaseHandle = new DatabaseHandle(this);
        Button btnSave = findViewById(R.id.btn_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username= "Linhbx123";
                String password= "123";
                String role= "admin";
                String campus= "HaNoi";
                long result = databaseHandle.insertUser(username,password,role,campus);
                if(result >=0){
                    Toast.makeText(RegisterUser.this,
                            "registered user successfuly", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}