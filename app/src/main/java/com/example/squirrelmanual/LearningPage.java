package com.example.squirrelmanual;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LearningPage extends AppCompatActivity  {

    private JSONArray topicList;
    private JSONArray currentProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.learningpage);

        SQUIRREL myApp = (SQUIRREL) getApplicationContext();
        int moduleId = myApp.getModuleId();
        int studentId = myApp.getStudentId();

        System.out.println(moduleId);

        new GetTopicList(moduleId).execute();
        new GetResultForLearningPage(studentId, moduleId, 1).execute();
        new GetResultForLearningPage(studentId, moduleId, 2).execute();
        new GetResultForLearningPage(studentId, moduleId, 3).execute();

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

    private class GetTopicList extends AsyncTask<Void, Void, String> {

        private int moduleId;

        public GetTopicList(int moduleId) {
            this.moduleId = moduleId;
            System.out.print("module_id = "+moduleId);
        }
        @Override
        protected String doInBackground(Void... params) {
            String urlString = SQUIRREL.baseURL+"/getTopicList?module_id="+this.moduleId;
            System.out.println("string = "+urlString);
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
                topicList = new JSONArray(result);
            } catch (JSONException e) {
                Log.e("MainActivity", "Error ", e);
            }
            // update UI with moduleList data
            TextView textViewA = findViewById(R.id.textViewA);
            TextView textViewB = findViewById(R.id.textViewB);
            TextView textViewC = findViewById(R.id.textViewC);
            try {
                if (topicList == null) {
                    textViewA.setText("Topic A");
                    textViewB.setText("Topic B");
                    textViewC.setText("Topic C");
                }
                else
                {
                    String moduleNameA = topicList.getJSONObject(0).getString("topic");
                    textViewA.setText(moduleNameA.substring(0, Math.min(moduleNameA.length(), 20)) + "...");
                    String moduleNameB = topicList.getJSONObject(1).getString("topic");
                    textViewB.setText(moduleNameB.substring(0, Math.min(moduleNameB.length(), 20)) + "...");
                    String moduleNameC = topicList.getJSONObject(2).getString("topic");
                    textViewC.setText(moduleNameC.substring(0, Math.min(moduleNameC.length(), 20)) + "...");
                }

            } catch (JSONException e) {
                Log.e("MainActivity", "Error ", e);
            }
        }
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
            ProgressBar progressBar = findViewById(R.id.progressBar1);
            TextView textView = findViewById(R.id.textView15);
            if (this.levelNumber == 2)
            {
                progressBar = findViewById(R.id.progressBar2);
                textView = findViewById(R.id.textView16);
            }
            else if (this.levelNumber == 3)
            {
                progressBar = findViewById(R.id.progressBar3);
                textView = findViewById(R.id.textView3);
            }


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
