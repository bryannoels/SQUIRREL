package com.example.squirrelmanual;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import java.io.IOException;
import android.os.AsyncTask;


public class MainActivity extends AppCompatActivity {

    private JSONArray moduleList;
    private JSONArray currentProgress;
    private String firstModule;
    private String secondModule;
    private String thirdModule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQUIRREL myApp = (SQUIRREL) getApplicationContext();
        String name = myApp.getName();
        int studentId = myApp.getStudentId();
        TextView textView = findViewById(R.id.textView);
        textView.setText("Hi, "+name+"!");

        // call the AsyncTask to fetch API
        new GetModuleList().execute();

        new GetResultForHomePage(studentId,1).execute();
        new GetResultForHomePage(studentId,2).execute();
        new GetResultForHomePage(studentId,3).execute();

        ImageView firstButton = findViewById(R.id.imageView10);
        ImageView secondButton = findViewById(R.id.imageView11);
        ImageView thirdButton = findViewById(R.id.imageView12);
        TextView profileButton = findViewById(R.id.profileButton);
        firstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Do something when the image is clicked
                Intent intent = new Intent(MainActivity.this, LearningPage.class);
                myApp.setModuleId(1);
                myApp.setModuleName(firstModule);
                startActivity(intent);
            }
        });
        secondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Do something when the image is clicked
                Intent intent = new Intent(MainActivity.this, LearningPage.class);
                myApp.setModuleId(2);
                myApp.setModuleName(secondModule);
                startActivity(intent);
            }
        });
        thirdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Do something when the image is clicked
                Intent intent = new Intent(MainActivity.this, LearningPage.class);
                myApp.setModuleId(3);
                myApp.setModuleName(thirdModule);
                startActivity(intent);
            }
        });
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Do something when the image is clicked
                Intent intent = new Intent(MainActivity.this, Settings.class);
                startActivity(intent);
            }
        });

        // other code here
    }

    private class GetModuleList extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            String urlString = SQUIRREL.baseURL+"/getModuleList";
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
                moduleList = new JSONArray(result);
            } catch (JSONException e) {
                Log.e("MainActivity", "Error ", e);
            }
            // update UI with moduleList data
            TextView textViewA = findViewById(R.id.textViewA);
            TextView textViewB = findViewById(R.id.textViewB);
            TextView textViewC = findViewById(R.id.textViewC);
            try {
                if (moduleList == null) {
                    textViewA.setText("Module A");
                    textViewB.setText("Module B");
                    textViewC.setText("Module C");
                }
                else
                {
                    String moduleNameA = moduleList.getJSONObject(0).getString("module_name");
                    firstModule = moduleNameA;
                    textViewA.setText(moduleNameA.substring(0, Math.min(moduleNameA.length(), 20)) + "...");
                    String moduleNameB = moduleList.getJSONObject(1).getString("module_name");
                    secondModule = moduleNameB;
                    textViewB.setText(moduleNameB.substring(0, Math.min(moduleNameB.length(), 20)) + "...");
                    String moduleNameC = moduleList.getJSONObject(2).getString("module_name");
                    thirdModule = moduleNameC;
                    textViewC.setText(moduleNameC.substring(0, Math.min(moduleNameC.length(), 20)) + "...");
                }

            } catch (JSONException e) {
                Log.e("MainActivity", "Error ", e);
            }
        }
    }

    private class GetResultForHomePage extends AsyncTask<Void, Void, String> {

        private int studentId;
        private int moduleId;

        public GetResultForHomePage(int studentId, int moduleId) {
            this.studentId = studentId;
            this.moduleId = moduleId;
        }
        @Override
        protected String doInBackground(Void... params) {
            String urlString = SQUIRREL.baseURL+"/getResultForHomePage?student_id="+studentId+"&module_id="+moduleId;
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
                currentProgress = new JSONArray(result);
            } catch (JSONException e) {
                Log.e("MainActivity", "Error ", e);
            }
            ProgressBar progressBar = findViewById(R.id.progressBar1);
            if (this.moduleId == 2)
                progressBar = findViewById(R.id.progressBar2);
            else if (this.moduleId == 3)
                progressBar = findViewById(R.id.progressBar3);

            int progressVal;
            try {
                progressVal = currentProgress.getJSONObject(0).getInt("count")*100/9;
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            progressBar.setProgress(progressVal, true);
        }
    }
}
