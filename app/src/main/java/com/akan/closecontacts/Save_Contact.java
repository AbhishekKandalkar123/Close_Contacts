package com.akan.closecontacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Save_Contact extends AppCompatActivity {
    EditText Contact_Name, Contact_Number;
    Button Save_Btn;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_contact);

        Contact_Name = findViewById(R.id.editTextTextPersonName3);
        Contact_Number = findViewById(R.id.editTextTextPersonName4);
        Save_Btn = findViewById(R.id.save_button);
        dbHandler = new DBHandler(Save_Contact.this);

        Save_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newName = Contact_Name.getText().toString();
                String newPhoneNo = Contact_Number.getText().toString();

                if (newName.equals("") || newPhoneNo.equals("")) {
                    Toast.makeText(Save_Contact.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean userCheckresult = dbHandler.checkPhoneNo(newPhoneNo);
                    if(userCheckresult == false) {
                        Boolean regresult = dbHandler.addNewContact(newName,newPhoneNo);
                        if(regresult){
                            Toast.makeText( Save_Contact.this, "Saved Successfully", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        else{
                            Toast.makeText(Save_Contact.this, "Save Unsuccessful", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(Save_Contact.this, "Contact already exists", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}