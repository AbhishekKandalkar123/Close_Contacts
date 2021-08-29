package com.akan.closecontacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class loginActivity extends AppCompatActivity {
    Button login;
    EditText Username;
    EditText Password;
    DBHandler md;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        md = new DBHandler(this);

        Username = findViewById(R.id.editTextTextPersonName);
        Password = findViewById(R.id.editTextTextPassword);

        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String USER = Username.getText().toString();
                String PASS = Password.getText().toString();
                if(USER.equals("") || PASS.equals("")){
                    Toast.makeText(loginActivity.this, "Please Enter the credentials", Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean result = md.checkUserNamePass(USER, PASS);
                    if(result){
                        Toast.makeText(loginActivity.this, "You are successfully Logged In", Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent(loginActivity.this, My_Contacts.class);
                        startActivity(intent);
                        finish();
                    }
                    else{
                        Toast.makeText(loginActivity.this, "User Not Registered,\nPlease Register first", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    public void openActivity(View v){
        Intent intent = new Intent(this, Registration.class);
        startActivity(intent);

    }
}