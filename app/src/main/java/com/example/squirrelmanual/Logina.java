package com.example.squirrelmanual;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

public class Logina extends AppCompatActivity  {

    private String phoneNumber;
    private String password;
    private JSONArray userDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logina);


        TextInputEditText inputPhoneNumber = findViewById(R.id.textInputEditText);
        TextInputEditText inputPassword = findViewById(R.id.textInputEditText2);

        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(v -> {
            phoneNumber = inputPhoneNumber.getText().toString();
            password = inputPassword.getText().toString();
            new GetUser().execute();
        });
    }

    private class GetUser extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            String urlString = SQUIRREL.baseURL+"/getUser?phone_number="+phoneNumber;
            System.out.println(urlString);
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
            SQUIRREL myApp = (SQUIRREL) getApplicationContext();
            // convert JSON string to JSONArray
            try {
                userDetails = new JSONArray(result);
                System.out.println("len = "+userDetails.length());
                if (userDetails != null && userDetails.length() > 0 )
                {
                    String expectedPassword = null;
                    try {
                        expectedPassword = userDetails.getJSONObject(0).getString("student_password");
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(expectedPassword);
                    if (Objects.equals(password, expectedPassword))
                    {
                        myApp.setPhone(phoneNumber);
                        myApp.setPassword(phoneNumber);
                        try {
                            myApp.setName(userDetails.getJSONObject(0).getString("student_name"));
                            myApp.setAge(Integer.parseInt(userDetails.getJSONObject(0).getString("student_age")));
                            myApp.setStudentId(Integer.parseInt(userDetails.getJSONObject(0).getString("student_id")));
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                        Intent intent = new Intent(Logina.this, MainActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Intent intent = new Intent(Logina.this, Login.class);
                        startActivity(intent);
                    }
                }
                else{
                    Intent intent = new Intent(Logina.this, Login.class);
                    startActivity(intent);
                }
            } catch (JSONException e) {
                Log.e("MainActivity", "Error ", e);
            }
        }
    }
}
