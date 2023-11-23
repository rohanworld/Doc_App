package com.android.rohan.hmh.docapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class ReceptionistLogin extends AppCompatActivity {
    EditText receptionist_code;
    Button receptionist_login_button;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receptionist_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        receptionist_code = findViewById(R.id.receptionist_Code);
        receptionist_login_button = findViewById(R.id.login_button);
        receptionist_login_button.setOnClickListener(view -> {
            String passcode = receptionist_code.getText().toString();
            if (passcode.equals("0000")){
                startActivity(new Intent(ReceptionistLogin.this, ReceptionistHomePage.class));
            }else{
                Toast.makeText(ReceptionistLogin.this, "Wrong Passcode", Toast.LENGTH_LONG).show();
                receptionist_code.requestFocus();
            }
        });
    }
}