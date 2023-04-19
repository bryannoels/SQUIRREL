package com.example.squirrelmanual;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

public class ProfileAge extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profileage);

        SQUIRREL myApp = (SQUIRREL) getApplicationContext();
        Button nextButton = findViewById(R.id.nextButton);
        TextInputLayout textInput = findViewById(R.id.textInputLayout2);

        nextButton.setOnClickListener(v -> {
            if (textInput.getEditText().getText().toString() != "") {
                myApp.setAge(Integer.parseInt(textInput.getEditText().getText().toString()));
                Intent intent = new Intent(ProfileAge.this, ProfileName.class);
                startActivity(intent);
            }
            else
            {
                Intent intent = new Intent(ProfileAge.this, Login.class);
                startActivity(intent);
            }
        });
    }
}
