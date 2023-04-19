package com.example.squirrelmanual;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView firstButton = findViewById(R.id.imageView10);
        ImageView secondButton = findViewById(R.id.imageView11);
        ImageView thirdButton = findViewById(R.id.imageView12);
        firstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Do something when the image is clicked
                Intent intent = new Intent(MainActivity.this, LearningPage.class);
                startActivity(intent);
            }
        });


        secondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Do something when the image is clicked
                Intent intent = new Intent(MainActivity.this, LearningPage.class);
                startActivity(intent);
            }
        });


        thirdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Do something when the image is clicked
                Intent intent = new Intent(MainActivity.this, LearningPage.class);
                startActivity(intent);
            }
        });

    }
}