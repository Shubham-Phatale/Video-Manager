package com.example.videomanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.videomanager.Database.DBhelper;

public class addcateg extends AppCompatActivity {
    EditText edt;
    Button btn;
    DBhelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addcateg);

        edt = findViewById(R.id.edttxt);
        btn = findViewById(R.id.addbtn);
        db = new DBhelper(this);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = edt.getText().toString().trim();
                if (str.equals("")){
                    Toast.makeText(getApplicationContext(), "Enter Category Name", Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean chkcat = db.chkcat(str);
                    if(chkcat == true){
                        Boolean insert = db.insertCategory(str);
                        if (insert == true){
                            Toast.makeText(getApplicationContext(), "Category Name Entered Successfully", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(addcateg.this,Categorypg.class );
                            startActivity(i);
                            finish();
                        }
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Category Name Already Exists", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
