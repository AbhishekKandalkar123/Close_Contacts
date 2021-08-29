package com.akan.closecontacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registration extends AppCompatActivity {
    EditText UserNameEdt, PasswordEdt, PhoneNumberEdt, ConfirmPassword;
    Button cancel, RegisterBtn;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        UserNameEdt = findViewById(R.id.editTextTextPersonName2);
        PasswordEdt = findViewById(R.id.editTextTextPassword2);
        PhoneNumberEdt = findViewById(R.id.editTextNumber);
        RegisterBtn = findViewById(R.id.button3);
        ConfirmPassword = findViewById(R.id.editTextTextPassword3);
        dbHandler = new DBHandler(Registration.this);

        RegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String UserName = UserNameEdt.getText().toString();
                String Password = PasswordEdt.getText().toString();
                String PhoneNumber = PhoneNumberEdt.getText().toString();
                String Repass = ConfirmPassword.getText().toString();
                if (UserName.equals("") || Password.equals("") || PhoneNumber.equals("") || Repass.equals("")) {
                    Toast.makeText(Registration.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(Password.equals(Repass)){
                        Boolean userCheckresult = dbHandler.checkUserName(UserName);
                        if(userCheckresult == false){
                            Boolean regresult = dbHandler.insertData(UserName, Password, PhoneNumber);
                            if(regresult == true){
                                Toast.makeText( Registration.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                            else{
                                Toast.makeText(Registration.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(Registration.this, "User already exists", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(Registration.this, "Password is not matching", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });

        cancel = findViewById(R.id.button4);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}