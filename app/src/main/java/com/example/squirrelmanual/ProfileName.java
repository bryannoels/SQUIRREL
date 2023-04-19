package com.example.squirrelmanual;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

public class ProfileName extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profilename);

        SQUIRREL myApp = (SQUIRREL) getApplicationContext();
        TextInputLayout textInput = findViewById(R.id.textInputLayout2);

        Button nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(v -> {
            if (textInput.getEditText().getText().toString() != "") {
                myApp.setName(textInput.getEditText().getText().toString());
                Intent intent = new Intent(ProfileName.this, ProfileNumber.class);
                startActivity(intent);
            }
            else
            {
                Intent intent = new Intent(ProfileName.this, Login.class);
                startActivity(intent);
            }
        });
    }
}
