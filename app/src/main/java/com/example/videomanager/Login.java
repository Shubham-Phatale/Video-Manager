package com.example.videomanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.videomanager.Database.DBhelper;

public class Login extends AppCompatActivity {
    EditText edtmail,edtpass;
    Button logbtn;
    TextView txtreg;
    DBhelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        edtmail = findViewById(R.id.email1);
        edtpass = findViewById(R.id.password);
        logbtn = findViewById(R.id.loginbtn);
        txtreg = findViewById(R.id.register);
        db = new DBhelper(this);

        logbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtmail.getText().toString().trim();
                String pass = edtpass.getText().toString().trim();
                Boolean chkemailpass = db.emailpass(email,pass);

                if (chkemailpass == true){
                    Intent i = new Intent(Login.this,Home.class);
                    startActivity(i);
                    finish();
                    Toast.makeText(getApplicationContext(), "Sucessfully Logged in", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Wrong Email or Password", Toast.LENGTH_SHORT).show();
                }
            }
        });
        txtreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this,Registration.class);
                startActivity(i);
                finish();
            }
        });
    }
}
