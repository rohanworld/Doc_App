package com.android.rohan.hmh.docapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DoctorLogin extends AppCompatActivity {

    EditText doctor_code;
    Button doctor_login_button;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receptionist_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        doctor_code = findViewById(R.id.receptionist_Code);
        doctor_login_button = findViewById(R.id.login_button);
        doctor_login_button.setOnClickListener(view -> {
            String passcode = doctor_code.getText().toString();
            if (passcode.equals("0000")){
                Toast.makeText(DoctorLogin.this, "OK Wait..", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(DoctorLogin.this, "Wrong Passcode", Toast.LENGTH_LONG).show();
                doctor_code.requestFocus();
            }
        });
    }
}