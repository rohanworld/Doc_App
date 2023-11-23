package com.android.rohan.hmh.docapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class ReceptionistHomePage extends AppCompatActivity {
    CardView todaysAppointments, addNewAppointment, patientsList, handovers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receptionist_home_page);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        addNewAppointment = findViewById(R.id.addNewAppointment);
        addNewAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent AddNewAppt = new Intent(ReceptionistHomePage.this, NewAppointmentPage.class);
                startActivity(AddNewAppt);
            }
        });

        todaysAppointments = findViewById(R.id.todaysAppointments);
        todaysAppointments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent AddNewAppt = new Intent(ReceptionistHomePage.this, AllAppointments.class);
                startActivity(AddNewAppt);
            }
        });
        patientsList = findViewById(R.id.patientsList);
        patientsList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent AddNewAppt = new Intent(ReceptionistHomePage.this, AllPatients.class);
                startActivity(AddNewAppt);
            }
        });
        handovers = findViewById(R.id.handovers);
        handovers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent AddNewAppt = new Intent(ReceptionistHomePage.this, AllHandovers.class);
                startActivity(AddNewAppt);
            }
        });
    }
}