package com.example.videomanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {
    Button Edtcat,Edtvid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        Edtcat = findViewById(R.id.cat);
        Edtvid = findViewById(R.id.video);

        Edtcat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this,Categorypg.class);
                startActivity(i);
            }
        });
        Edtvid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this,videopg.class);
                startActivity(i);
            }
        });
    }
}
