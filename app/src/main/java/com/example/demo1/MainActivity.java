package com.example.demo1;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.demo1.bean.UserInfo;
import com.example.demo1.database.DatabaseHandle;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText editUsername;
    private EditText editPassword;
    private Spinner spinnerCampus;
    private CheckBox checkBoxRemember;
    private RadioButton radioStaf;
    private RadioButton radioManager;
    private Button btnLogin;

    private Button btnEditUser;
    private String role;
    public String[] array = new String[]{"hanoi","can tho"};

    private DatabaseHandle databaseHandle = null;
    private ActivityResultLauncher activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getResultCode() == 2){
                Intent intent = result.getData();
                editUsername.setText(intent.getStringExtra("USERNAME"));
                editPassword.setText(intent.getStringExtra("PASSWORD"));
            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editUsername = findViewById(R.id.edit_user_name);
        editPassword = findViewById(R.id.edit_password);
        radioStaf = findViewById(R.id.radio_staff);
        radioManager = findViewById(R.id.radio_manager);
        spinnerCampus = findViewById(R.id.spiner_campus);
        checkBoxRemember = findViewById(R.id.check_box_remember);
        btnLogin = findViewById(R.id.button_login);
        btnEditUser = findViewById(R.id.button_edit_user);
        Button btnCreateProduct = findViewById(R.id.button_create_product);
        databaseHandle = new DatabaseHandle(this);

        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this,
                R.array.campus, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_list_item_checked);
        spinnerCampus.setAdapter(arrayAdapter);
        spinnerCampus.setOnItemSelectedListener(this);

        radioStaf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                role = radioStaf.getText().toString();
            }
        });
        radioManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                role = radioManager.getText().toString();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editUsername.getText().toString().trim();
                String password = editPassword.getText().toString().trim();
                boolean remember = checkBoxRemember.isChecked();
                if(username.isEmpty()){
                    editUsername.setError("username is required!!");
                    return;
                }
                String message ="";
//                String message =
//                        "Username: " + username + "\n" +
//                        "Password: " + password + "\n" +
//                        "Role: " + role + "\n" +
//                        "Remember: " + remember;
//                Intent intent = new Intent(MainActivity.this, relative_layout.class);
//                startActivity(intent);

                UserInfo userInfo  = databaseHandle.getUser(username);
                if(userInfo.getUsername() != null){
                    Log.d(getClass().getSimpleName(),userInfo.getUsername());
                }else{
                    Log.d(getClass().getSimpleName(),"null");

                }
                if(userInfo != null && userInfo.getUsername().equals(username) && userInfo.getPassword().equals(password)){

                    Intent intent = new Intent(MainActivity.this, relative_layout.class);
                    startActivity(intent);

                    message="đăng nhập ok";
                    Toast.makeText(MainActivity.this, message , Toast.LENGTH_SHORT).show();

                    intent.putExtra("USERNAME", username);
                    intent.putExtra("PASSWORD", password);

                    activityResultLauncher.launch(intent);
                }else{
                    message="Sai tk mk rồi cu";
                    Toast.makeText(MainActivity.this, message , Toast.LENGTH_SHORT).show();
                }




            }
        });

        btnEditUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editUsername.getText().toString().trim();
                String password = editPassword.getText().toString().trim();
                SharedPreferences sharedPreferences = getSharedPreferences("USERINFO", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("USERNAME", username);
                editor.putString("PASSWORD", password);
                editor.commit();


                Intent intent = new Intent(MainActivity.this, relative_layout.class);
                startActivity(intent);
            }
        });

        btnCreateProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CeateProductActivity.class);
                startActivity(intent);
            }
        });
//    btnEditUser.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//           Intent intent = new Intent(MainActivity.this, relative_layout.class);
//            startActivity(intent);
//        }
//    });
    }
    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);
        if(reqCode == 2){
            editUsername.setText(data.getStringExtra("USERNAME"));
            editPassword.setText(data.getStringExtra("PASSWORD"));
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String seletedItem = parent.getItemAtPosition(position).toString();
        Toast.makeText(this, "selected Item" + seletedItem, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}
