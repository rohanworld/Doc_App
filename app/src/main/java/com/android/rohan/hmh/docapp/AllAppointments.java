package com.android.rohan.hmh.docapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class AllAppointments extends AppCompatActivity {
    FirebaseFirestore db;
    ListView listViewForAppointments;
    ArrayList<Appointment> mAppointments;
    ArrayList<String> filterChips = new ArrayList<>();
    AppointmentAdapter adapter1;
    Toolbar tb;
    ChipGroup chipGroup;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_appointments);
        tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);

        listViewForAppointments = findViewById(R.id.listViewForAppointments);
        chipGroup = findViewById(R.id.chipGroup);

        db = FirebaseFirestore.getInstance();
        mAppointments = new ArrayList<>();
        showAllAppointments();
        adapter1 = new AppointmentAdapter(AllAppointments.this, mAppointments);
        listViewForAppointments.setAdapter((ListAdapter) adapter1);

        filterChips.add("Male");
        filterChips.add("Female");
        filterChips.add("Inpatient");
        filterChips.add("Outpatient");
        filterChips.add("Age");
        int chipCounter = 1;
        for (String s:filterChips) {
            Chip chip = (Chip) LayoutInflater.from(AllAppointments.this).inflate(R.layout.chip_layout, null);
            chip.setText(s);
            chip.setId(chipCounter++);
            chipGroup.addView(chip);
        }
        chipGroup.setOnCheckedStateChangeListener(new ChipGroup.OnCheckedStateChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull ChipGroup group, @NonNull List<Integer> checkedIds) {
                if(checkedIds.isEmpty()){
                    showAllAppointments();
                    Toast.makeText(AllAppointments.this, "No Filter Selected", Toast.LENGTH_SHORT).show();
                }else{
                    for(int i:checkedIds){
                        Chip chip = findViewById(i);
                        Toast.makeText(AllAppointments.this, chip.getText(), Toast.LENGTH_LONG).show();
                        if (chip.getText().toString().toLowerCase().equals("male") || chip.getText().toString().toLowerCase().equals("female")) {
                            filterAppointmentsByGender(chip.getText().toString().toLowerCase());
                        } else if (chip.getText().toString().toLowerCase().equals("outpatient") || chip.getText().toString().toLowerCase().equals("inpatient")) {
                            filterAppointmentsByInOrOut(chip.getText().toString().toLowerCase());
                        } else {
                            sortAppointmentsByAge();
                        }

                    }
                }
            }
        });

    }

    private void sortAppointmentsByAge(){
        mAppointments.clear();
        adapter1.notifyDataSetChanged();
        db.collection("appointments")
                .orderBy("age", Query.Direction.ASCENDING) // or Query.Direction.DESCENDING for descending order
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                            Appointment appointment = document.toObject(Appointment.class);
                            mAppointments.add(appointment);
                        }
                        adapter1.notifyDataSetChanged();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AllAppointments.this, "Failed to fetch appointments", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void filterAppointmentsByInOrOut(String filterKeyword) {
        mAppointments.clear();
        adapter1.notifyDataSetChanged();
        db.collection("appointments").whereEqualTo("inOrOut", filterKeyword).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(QueryDocumentSnapshot document: queryDocumentSnapshots){
                    Appointment appointment = document.toObject(Appointment.class);
                    mAppointments.add(appointment);
                }
                adapter1.notifyDataSetChanged();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AllAppointments.this, "Failed to fetch "+filterKeyword, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void filterAppointmentsByGender(String filterKeyword) {
        mAppointments.clear();
        adapter1.notifyDataSetChanged();
        db.collection("appointments").whereEqualTo("gender", filterKeyword).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(QueryDocumentSnapshot document: queryDocumentSnapshots){
                    Appointment appointment = document.toObject(Appointment.class);
                    mAppointments.add(appointment);
                }
                adapter1.notifyDataSetChanged();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AllAppointments.this, "Failed to fetch "+filterKeyword, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showAllAppointments() {
        db.collection("appointments").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(QueryDocumentSnapshot document: queryDocumentSnapshots){
                    Appointment appointment = document.toObject(Appointment.class);
                    String uniqueId = db.collection("appointments").document().getId();
                    appointment.setMid(document.getId());
                    mAppointments.add(appointment);
                }
                adapter1.notifyDataSetChanged();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AllAppointments.this, "Error Fetching appointments", Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        if (searchView != null) {
            searchView.setQueryHint("Search Here...");
        }

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
//            if(adapter1!=null){
//                adapter1.getFilter().filter(newText);
//            }
                filterAppointmentsBySearch(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
    private void filterAppointmentsBySearch(String query) {
        ArrayList<Appointment> filteredAppointments = new ArrayList<>();

        for (Appointment appointment : mAppointments) {
            if (appointment.getName().toLowerCase().contains(query.toLowerCase())
                    || String.valueOf(appointment.getAge()).contains(query)
                    || appointment.getType().toLowerCase().contains(query.toLowerCase())) {
                filteredAppointments.add(appointment);
            }
        }

        adapter1 = new AppointmentAdapter(AllAppointments.this, filteredAppointments);
        listViewForAppointments.setAdapter(adapter1);
    }
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        tb.setTitle("Appointmetn from post create");
    }
}