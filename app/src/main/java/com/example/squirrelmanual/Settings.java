package com.example.squirrelmanual;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

public class Settings extends AppCompatActivity  {

    private int type = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        SQUIRREL myApp = (SQUIRREL) getApplicationContext();
        int age = myApp.getAge();
        String name = myApp.getName();
        String phoneNumber = myApp.getPhone();
        String password = myApp.getPassword();
        int studentId = myApp.getStudentId();

        TextInputEditText editAge = findViewById(R.id.editAge);
        editAge.setText(age+"");
        TextInputEditText editName = findViewById(R.id.editName);
        editName.setText(name);
        TextInputEditText editPhoneNumber = findViewById(R.id.editPhoneNumber);
        editPhoneNumber.setText(phoneNumber);
        TextInputEditText editPassword = findViewById(R.id.editPassword);
        editPassword.setText(password);

        Button updateButton = findViewById(R.id.updateButton);
        updateButton.setOnClickListener(v -> {
            int updatedAge = Integer.parseInt(editAge.getText().toString());
            String updatedName = editName.getText().toString();
            String updatedPhoneNumber = editPhoneNumber.getText().toString();
            String updatedPassword = editPassword.getText().toString();
            myApp.setAge(updatedAge);
            myApp.setName(updatedName);
            myApp.setPhone(updatedPhoneNumber);
            myApp.setPassword(updatedPassword);
            type = 1;
            new UpdateUser(updatedAge, updatedName, updatedPhoneNumber, updatedPassword, studentId).execute();
        });
        Button logoutButton = findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(v -> {
            int updatedAge = Integer.parseInt(editAge.getText().toString());
            String updatedName = editName.getText().toString();
            String updatedPhoneNumber = editPhoneNumber.getText().toString();
            String updatedPassword = editPassword.getText().toString();
            myApp.setAge(updatedAge);
            myApp.setName(updatedName);
            myApp.setPhone(updatedPhoneNumber);
            myApp.setPassword(updatedPassword);
            type = 2;
            new UpdateUser(updatedAge, updatedName, updatedPhoneNumber, updatedPassword, studentId).execute();
        });
        TextView learnButton = findViewById(R.id.learnButton);
        learnButton.setOnClickListener(v -> {
            Intent intent = new Intent(Settings.this, MainActivity.class);
            startActivity(intent);
        });
    }

    private class UpdateUser extends AsyncTask<Void, Void, String> {

        private int age;
        private String name;
        private String phoneNumber;
        private String password;
        private int studentId;

        public UpdateUser(int age, String name, String phoneNumber, String password, int studentId) {
            this.age = age;
            this.name = name;
            this.phoneNumber = phoneNumber;
            this.password = password;
            this.studentId = studentId;
        }
        @Override
        protected String doInBackground(Void... params) {
            String urlString = SQUIRREL.baseURL+"/updateUser?student_name="+this.name+"&phone_number="+this.phoneNumber
                    +"&student_age="+this.age+"&student_password="+this.password+"&student_id="+this.studentId;
            String result = null;
            try {
                URL url = new URL(urlString);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setReadTimeout(10000 /* milliseconds */);
                urlConnection.setConnectTimeout(15000 /* milliseconds */);
                urlConnection.connect();
                int responseCode = urlConnection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = in.readLine()) != null) {
                        response.append(line);
                    }
                    in.close();
                    result = response.toString();
                } else {
                    result = "none";
                }
            } catch (IOException e) {
                Log.e("MainActivity", "Error ", e);
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            // convert JSON string to JSONArray
            Intent intent;
            if (type == 1)
                intent = new Intent(Settings.this, MainActivity.class);
            else
                intent = new Intent(Settings.this, Login.class);
            startActivity(intent);
        }
    }
}
