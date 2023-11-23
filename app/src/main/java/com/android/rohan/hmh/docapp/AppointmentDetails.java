package com.android.rohan.hmh.docapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class AppointmentDetails extends AppCompatActivity {
    int age;
    TextView sampleTextView;
    String name, gender, phoneNumber, address, type, doctorName, inOrOut, date, time, remarks, documentID;
    Button deleteApptBtn;
    FirebaseFirestore db;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_details);


        sampleTextView = findViewById(R.id.sampleTextView);
        deleteApptBtn = findViewById(R.id.deleteApptBtn);

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
        sampleTextView.setText(name+"\n"+age+"\n"+gender+"\n"+phoneNumber+"\n"+address+"\n"+type+"\n"+doctorName+"\n"+inOrOut+"\n"+date+"\n"+time+"\n"+remarks+"\n"+documentID);

        deleteApptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteAppointment();
            }
        });
    }


    private void deleteAppointment() {
        db = FirebaseFirestore.getInstance();
        if(documentID!=null){
            db.collection("appointments").document(documentID).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(AppointmentDetails.this, "Appointment Deleted Successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(AppointmentDetails.this, "COuldnot Delete error", Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            Toast.makeText(this, "No DOcument ID, it is null", Toast.LENGTH_SHORT).show();
        }
    }

    private void navigateToEditActivity() {
        if (documentID != null) {
//            Intent intent = new Intent(AppointmentDetails.this, EditAppointmentActivity.class);
//            intent.putExtra("documentId", documentID);
//            startActivity(intent);
        } else {
            Toast.makeText(AppointmentDetails.this, "Unable to edit appointment", Toast.LENGTH_SHORT).show();
        }
    }

}