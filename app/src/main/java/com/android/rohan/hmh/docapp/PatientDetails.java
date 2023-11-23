package com.android.rohan.hmh.docapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

public class PatientDetails extends AppCompatActivity {
    int age;
    TextView sampleTextView;
    String name, gender, phoneNumber, address, type, doctorName, inOrOut, date, time, remarks, documentID;

     @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_details);


        sampleTextView = findViewById(R.id.sampleTextView);

        documentID = getIntent().getStringExtra("documentId");
        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            name = extras.getString("name");
            age = Integer.parseInt(extras.getString("age"));
            gender = extras.getString("gender");
            phoneNumber = extras.getString("phoneNumber");
            address = extras.getString("address");
            type = extras.getString("type");
            doctorName = extras.getString("doctor");
            inOrOut = extras.getString("inOrOut");
            date = extras.getString("date");
            time  = extras.getString("time");
            remarks = extras.getString("remarks");
        }
        sampleTextView.setText(name+"\n"+age+"\n"+gender+"\n"+phoneNumber+"\n"+address+"\n"+type+"\n"+doctorName+"\n"+inOrOut+"\n"+remarks+"\n"+documentID);


    }





}