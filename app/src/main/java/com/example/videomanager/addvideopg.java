package com.example.videomanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.videomanager.Database.DBhelper;

public class addvideopg extends AppCompatActivity {
    EditText edt1,edt2,edt3;
    Button btnadd;
    DBhelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addvideopg);

        edt1 = findViewById(R.id.vname);
        edt2 = findViewById(R.id.vcode);
        edt3 = findViewById(R.id.vcatname);
        btnadd = findViewById(R.id.vaddbtn);
        db = new DBhelper(this);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String vname = edt1.getText().toString().trim();
                String vcode = edt2.getText().toString().trim();
                String vcat = edt3.getText().toString().trim();
                if (vname.equals("")||vcode.equals("")||vcat.equals("")){
                    Toast.makeText(getApplicationContext(), "Fields Are Empty", Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean checkvideo = db.chkvdo(vcode);
                    if (checkvideo == true){
                        Boolean insert = db.insertvideocode(vname,vcode,vcat);
                        if (insert == true){
                            Toast.makeText(getApplicationContext(), "Video Name Entered Successfully", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(addvideopg.this,videopg.class );
                            startActivity(i);
                            finish();
                        }
                    }
                }
            }
        });
    }
}
