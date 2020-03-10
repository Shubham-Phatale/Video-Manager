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

public class Registration extends AppCompatActivity {

    EditText edtname, edtmail, edtnumber, edtpass, edtcpass;
    Button regbtn;
    TextView logpg;
    DBhelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        db = new DBhelper(this);
        edtname = findViewById(R.id.name);
        edtmail = findViewById(R.id.email);
        edtnumber = findViewById(R.id.number);
        edtpass = findViewById(R.id.pass);
        edtcpass = findViewById(R.id.cnfpass);
        regbtn = findViewById(R.id.registerbtn);
        logpg = findViewById(R.id.login);


        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtname.getText().toString().trim();
                String mail = edtmail.getText().toString().trim();
                String number = edtnumber.getText().toString().trim();
                String pass = edtpass.getText().toString().trim();
                String cpass = edtcpass.getText().toString().trim();

                if(name.equals("")||mail.equals("")||number.equals("")||pass.equals("")||cpass.equals("")){
                    Toast.makeText(getApplicationContext(), "fields are empty", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(pass.equals(cpass)){
                        Boolean chkmail = db.chkemail(mail);
                        if (chkmail==true){
                            Boolean insert = db.insert(name,mail,number,pass);
                            if (insert == true){
                                Toast.makeText(getApplicationContext(), "Registered Sucessfully", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(Registration.this,Login.class);
                                startActivity(i);
                                finish();
                            }
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Email already Exists", Toast.LENGTH_SHORT).show();
                        }
                    }else
                    Toast.makeText(getApplicationContext(), "Something's wrong!! Please check ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        logpg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Registration.this,Login.class);
                startActivity(i);
                finish();
            }
        });
    }
}

