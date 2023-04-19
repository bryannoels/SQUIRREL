package com.example.squirrelmanual;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

public class ProfileNumber extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profilenumber);

        SQUIRREL myApp = (SQUIRREL) getApplicationContext();
        TextInputLayout textInput = findViewById(R.id.textInputLayout2);

        Button nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(v -> {
            if (textInput.getEditText().getText().toString() != "") {
                myApp.setPhone(textInput.getEditText().getText().toString());
                Intent intent = new Intent(ProfileNumber.this, ProfilePassword.class);
                startActivity(intent);
            }
            else
            {
                Intent intent = new Intent(ProfileNumber.this, ProfileNumber.class);
                startActivity(intent);
            }
        });
    }
}
