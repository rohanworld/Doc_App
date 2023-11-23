package com.android.rohan.hmh.docapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    ImageButton doc_img, receptionist_img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        doc_img = findViewById(R.id.doc_imageButton);
        receptionist_img = findViewById(R.id.receptionist_imageButton);

        doc_img.setOnClickListener(view -> {
            Snackbar.make(view, "Welcome Doctor!!", Snackbar.LENGTH_SHORT)
                    .setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_FADE)
                    .setBackgroundTint(Color.parseColor("#ff0000"))
                    .show();
            startActivity(new Intent(MainActivity.this, DoctorLogin.class));
        });
        receptionist_img.setOnClickListener(view -> {
            Snackbar.make(view, "Welcome Receptionist!!", Snackbar.LENGTH_SHORT)
                    .setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_FADE)
                    .setBackgroundTint(Color.parseColor("#ff0000"))
                    .show();
            startActivity(new Intent(MainActivity.this, ReceptionistLogin.class));
        });
    }
}