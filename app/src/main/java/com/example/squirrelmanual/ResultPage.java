package com.example.squirrelmanual;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ResultPage extends AppCompatActivity  {

    private JSONArray currentProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultpage);

        SQUIRREL myApp = (SQUIRREL) getApplicationContext();
        int moduleId = myApp.getModuleId();
        int studentId = myApp.getStudentId();
        int levelNumber = myApp.getLevelNumber();

        new GetResultForLearningPage(studentId, moduleId, levelNumber).execute();

        Button continueButton = findViewById(R.id.continueButton);
        continueButton.setOnClickListener(v -> {
            Intent intent = new Intent( ResultPage.this, LearningPage.class);
            startActivity(intent);
        });
    }

    private class GetResultForLearningPage extends AsyncTask<Void, Void, String> {

        private int studentId;
        private int moduleId;
        private int levelNumber;

        public GetResultForLearningPage(int studentId, int moduleId, int levelNumber) {
            this.studentId = studentId;
            this.moduleId = moduleId;
            this.levelNumber = levelNumber;
        }
        @Override
        protected String doInBackground(Void... params) {
            String urlString = SQUIRREL.baseURL+"/getResultForLearningPage?student_id="+studentId+
                    "&module_id="+moduleId+"&level_number="+levelNumber;
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
            ProgressBar progressBar = findViewById(R.id.progressBar);
            TextView textView = findViewById(R.id.textView15);


            int progressVal = 0;
            try {
                progressVal = currentProgress.getJSONObject(0).getInt("count")*100/3;
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            progressBar.setProgress(progressVal, true);
            try {
                textView.setText(currentProgress.getJSONObject(0).getString("count")+"/3");
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
