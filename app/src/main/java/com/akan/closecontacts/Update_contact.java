package com.akan.closecontacts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Update_contact extends AppCompatActivity {
    EditText NameEdt;
    EditText NumberEdt;
    Button Delete ,Update;
    DBHandler dbHandler;
    ImageView phoneCall;
    public static final int request_Call = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_contact);
        NameEdt = findViewById(R.id.editTextTextPersonName3);
        NumberEdt = findViewById(R.id.editTextTextPersonName4);
        NumberEdt.setInputType(InputType.TYPE_CLASS_NUMBER);
        Delete = findViewById(R.id.Delete_id);
        Update = findViewById(R.id.update_id);
        String NameOrg, NumberOrg;

        phoneCall = findViewById(R.id.phone_call);
        phoneCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall();
            }
        });

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

    public void makePhoneCall(){
        String number = NumberEdt.getText().toString();
        if(number.trim().length()>0){
            if(ContextCompat.checkSelfPermission(Update_contact.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(Update_contact.this, new String[]{Manifest.permission.CALL_PHONE}, request_Call);
            }else{
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == request_Call) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall();
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}