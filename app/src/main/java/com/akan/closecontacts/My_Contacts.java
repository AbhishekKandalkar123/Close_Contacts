package com.akan.closecontacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

public class My_Contacts extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<LoginModal> contactModalArrayList;
    private ContactRVAdapter contactRVAdapter;
    private DBHandler dbHandler;
    FloatingActionButton flbutton;
    ImageView arrow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_contacts);
        contactModalArrayList = new ArrayList<>();

        dbHandler = new DBHandler(My_Contacts.this);
        contactModalArrayList = dbHandler.readContacts();

        contactRVAdapter = new ContactRVAdapter(contactModalArrayList, My_Contacts.this);
        recyclerView = findViewById(R.id.recyclerViewid);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(My_Contacts.this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter((contactRVAdapter));

        flbutton = findViewById(R.id.floatingActionButton);
        flbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(My_Contacts.this, Save_Contact.class);
                startActivity(intent);

            }
        });
    }

    protected void onRestart() {
        super.onRestart();
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
    }
}