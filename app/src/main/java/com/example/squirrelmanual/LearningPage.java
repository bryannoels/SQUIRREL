package com.example.squirrelmanual;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class LearningPage extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.learningpage);

        ImageView firstButton = findViewById(R.id.imageView5);
        firstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Do something when the image is clicked
                Intent intent = new Intent(LearningPage.this, TestPage.class);
                startActivity(intent);
            }
        });
        ImageView secondButton = findViewById(R.id.imageView18);
        secondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Do something when the image is clicked
                Intent intent = new Intent(LearningPage.this, TestPage.class);
                startActivity(intent);
            }
        });
        ImageView thirdButton = findViewById(R.id.imageView19);
        thirdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Do something when the image is clicked
                Intent intent = new Intent(LearningPage.this, TestPage.class);
                startActivity(intent);
            }
        });
        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent( LearningPage.this, MainActivity.class);
            startActivity(intent);
        });
    }
}
