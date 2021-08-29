package com.akan.closecontacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Update_contact extends AppCompatActivity {
    EditText NameEdt;
    EditText NumberEdt;
    Button Delete ,Update;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_contact);
        NameEdt = findViewById(R.id.editTextTextPersonName3);
        NumberEdt = findViewById(R.id.editTextTextPersonName4);
        Delete = findViewById(R.id.Delete_id);
        Update = findViewById(R.id.update_id);
        String NameOrg, NumberOrg;

        dbHandler = new DBHandler(Update_contact.this);

        NameOrg = getIntent().getStringExtra("name");
        NumberOrg = getIntent().getStringExtra("phoneNo");

        NameEdt.setText(NameOrg);
        NumberEdt.setText(NumberOrg);

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHandler.updateContacts(NumberOrg, NumberEdt.getText().toString(), NameEdt.getText().toString());
                Toast.makeText(Update_contact.this, "Contact Updated..", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHandler.deleteContacts(NumberOrg);
                Toast.makeText(Update_contact.this, "Deleted the Contact", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }

    public void onBackPressed(){
        super.onBackPressed();
        finish();
    }
}