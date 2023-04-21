package com.example.squirrelmanual;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ProfilePassword extends AppCompatActivity  {

    private JSONArray newUser;
    private String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profilepassword);

        SQUIRREL myApp = (SQUIRREL) getApplicationContext();
        int age = myApp.getAge();
        String name = myApp.getName();
        String phoneNumber = myApp.getPhone();

        TextInputLayout textInput = findViewById(R.id.textInputLayout2);

        Button nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(v -> {
            if (textInput.getEditText().getText().toString() != "") {
                String password = textInput.getEditText().getText().toString();
                myApp.setPassword(password);
                new CreateUser(age, name, phoneNumber, password).execute();
            }
            else
            {
                Intent intent = new Intent(ProfilePassword.this, Login.class);
                startActivity(intent);
            }
        });
    }

    private class CreateUser extends AsyncTask<Void, Void, String> {

        private int age;
        private String name;
        private String phoneNumber;
        private String password;

        public CreateUser(int age, String name, String phoneNumber, String password) {
            this.age = age;
            this.name = name;
            this.phoneNumber = phoneNumber;
            this.password = password;
        }
        @Override
        protected String doInBackground(Void... params) {
            String urlString = SQUIRREL.baseURL+"/createUser?student_name="+this.name+"&phone_number="+this.phoneNumber
                    +"&student_age="+this.age+"&student_password="+this.password;
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
                    result = "error";
                }
            } catch (IOException e) {
                Log.e("MainActivity", "Error ", e);
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            // convert JSON string to JSONArray
            try {
                newUser = new JSONArray(result);
            } catch (JSONException e) {
                Log.e("MainActivity", "Error ", e);
            }
            try {
                int student_id = newUser.getJSONObject(0).getInt("student_id");
                SQUIRREL myApp = (SQUIRREL) getApplicationContext();
                myApp.setStudentId(student_id);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            Intent intent = new Intent(ProfilePassword.this, MainActivity.class);
            startActivity(intent);
        }
    }
}
