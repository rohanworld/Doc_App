package com.android.rohan.hmh.docapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class AllHandovers extends AppCompatActivity {

    ArrayList<String> handovers = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    ListView handoversListView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_handovers);
        handoversListView = findViewById(R.id.handoversList);

        handovers.add("Dr.Elon handed over to Dr.Musk \n Dr.Elon will be on leave from 16th November to 20th November");
        handovers.add("Dr.Jeff handed over to Dr.Bezos \n Dr.Jeff will be on leave from 16th November to 20th November");
        handovers.add("Dr.Sundar handed over to Dr.pichai \n Dr.Sundar will be on leave from 16th November to 20th November");
        handovers.add("Dr.Mark handed over to Dr.Zuckerberg \n Dr.Mark will be on leave from 16th November to 20th November");
        handovers.add("Dr.Satya handed over to Dr.Nadella \n Dr.Satya will be on leave from 16th November to 20th November");
        handovers.add("Dr.Tim handed over to Dr.Cook \n Dr.Tim will be on leave from 16th November to 20th November");
        adapter = new ArrayAdapter<String>(AllHandovers.this, android.R.layout.simple_list_item_1, handovers);
        handoversListView.setAdapter(adapter);
    }
}