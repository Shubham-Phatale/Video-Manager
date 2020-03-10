package com.example.videomanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.videomanager.Database.DBhelper;

import java.util.ArrayList;

public class Categorypg extends AppCompatActivity {
    ListView lv;
    ImageView addbtn;
    ArrayList<String> al = new ArrayList<>();
    ArrayAdapter<String> adapter;
    DBhelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categorypg);

        addbtn = findViewById(R.id.add);
        db = new DBhelper(this);
        al = db.getCategoryName();
        lv = findViewById(R.id.list);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, al);
        lv.setAdapter(adapter);

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Categorypg.this,addcateg.class);
                startActivity(i);
                finish();
            }
        });

    }
}
