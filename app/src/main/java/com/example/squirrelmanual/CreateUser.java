package com.example.squirrelmanual;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import org.json.JSONArray;

public class CreateUser extends AppCompatActivity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createuser);

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(CreateUser.this, Login.class);
            startActivity(intent);
        });
        Button nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(v -> {
            Intent intent = new Intent(CreateUser.this, ProfileAge.class);
            startActivity(intent);
        });
    }
}
