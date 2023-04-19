package com.example.squirrelmanual;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class TestPage extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testpage);

        Button buttonA = findViewById(R.id.buttonA);
        buttonA.setOnClickListener(v -> {
            Intent intent = new Intent(TestPage.this, TestPageWrong.class);
            startActivity(intent);
        });
        Button buttonB = findViewById(R.id.buttonB);
        buttonB.setOnClickListener(v -> {
            Intent intent = new Intent(TestPage.this, TestPageWrong.class);
            startActivity(intent);
        });
        Button buttonC = findViewById(R.id.buttonC);
        buttonC.setOnClickListener(v -> {
            Intent intent = new Intent(TestPage.this, TestPageWrong.class);
            startActivity(intent);
        });
        Button buttonD = findViewById(R.id.buttonD);
        buttonD.setOnClickListener(v -> {
            Intent intent = new Intent(TestPage.this, TestPageCorrect.class);
            startActivity(intent);
        });

    }
}
