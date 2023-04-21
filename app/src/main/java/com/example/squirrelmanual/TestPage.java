package com.example.squirrelmanual;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestPage extends AppCompatActivity  {

    private JSONArray currentQuestion;
    private JSONArray choices;
    private int answer = 1;
    private int questionId = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testpage);

        SQUIRREL myApp = (SQUIRREL) getApplicationContext();
        int moduleId = myApp.getModuleId();
        int levelNumber = myApp.getLevelNumber();
        int questionNumber = myApp.getQuestionNumber();
        new GetQuestionText(moduleId, levelNumber, questionNumber).execute();

        SeekBar seek = findViewById(R.id.seekBar);
        seek.setProgress(questionNumber);

        Button buttonA = findViewById(R.id.buttonA);
        buttonA.setOnClickListener(v -> {
            Intent intent;
            if (answer == 1)
                intent = new Intent(TestPage.this, TestPageCorrect.class);
            else
                intent = new Intent(TestPage.this, TestPageWrong.class);
            startActivity(intent);
        });
        Button buttonB = findViewById(R.id.buttonB);
        buttonB.setOnClickListener(v -> {
            Intent intent;
            if (answer == 2)
                intent = new Intent(TestPage.this, TestPageCorrect.class);
            else
                intent = new Intent(TestPage.this, TestPageWrong.class);
            startActivity(intent);
        });
        Button buttonC = findViewById(R.id.buttonC);
        buttonC.setOnClickListener(v -> {
            Intent intent;
            if (answer == 3)
                intent = new Intent(TestPage.this, TestPageCorrect.class);
            else
                intent = new Intent(TestPage.this, TestPageWrong.class);
            startActivity(intent);
        });
        Button buttonD = findViewById(R.id.buttonD);
        buttonD.setOnClickListener(v -> {
            Intent intent;
            if (answer == 4)
                intent = new Intent(TestPage.this, TestPageCorrect.class);
            else
                intent = new Intent(TestPage.this, TestPageWrong.class);
            startActivity(intent);
        });

    }

    private class GetQuestionText extends AsyncTask<Void, Void, String> {


        private int moduleId;
        private int levelNumber;
        private int questionNumber;

        public GetQuestionText(int moduleId, int levelNumber, int questionNumber) {
            this.moduleId = moduleId;
            this.levelNumber = levelNumber;
            this.questionNumber = questionNumber;
        }
        @Override
        protected String doInBackground(Void... params) {
            String urlString = SQUIRREL.baseURL+"/getQuestionText?module_id="+moduleId
                    +"&level_number="+levelNumber+"&question_number="+questionNumber;
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
                currentQuestion = new JSONArray(result);
            } catch (JSONException e) {
                Log.e("MainActivity", "Error ", e);
            }
            TextView title = findViewById(R.id.textView17);
            try {
                title.setText(currentQuestion.getJSONObject(0).getString("question_text"));
                answer = currentQuestion.getJSONObject(0).getInt("question_answer");
                questionId = currentQuestion.getJSONObject(0).getInt("question_id");
                SQUIRREL myApp = (SQUIRREL) getApplicationContext();
                myApp.setCurrentAnswer(answer);
                new GetChoices(questionId).execute();
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private class GetChoices extends AsyncTask<Void, Void, String> {


        private int questionId;

        public GetChoices(int questionId) {
            this.questionId = questionId;
        }
        @Override
        protected String doInBackground(Void... params) {
            String urlString = SQUIRREL.baseURL+"/getChoices?question_id="+questionId;
            System.out.println("urlString = "+urlString);
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
                choices = new JSONArray(result);
            } catch (JSONException e) {
                Log.e("MainActivity", "Error ", e);
            }
            Button buttonA = findViewById(R.id.buttonA);
            Button buttonB = findViewById(R.id.buttonB);
            Button buttonC = findViewById(R.id.buttonC);
            Button buttonD = findViewById(R.id.buttonD);
            try {
                buttonA.setText(choices.getJSONObject(0).getString("choice_text"));
                buttonB.setText(choices.getJSONObject(1).getString("choice_text"));
                buttonC.setText(choices.getJSONObject(2).getString("choice_text"));
                buttonD.setText(choices.getJSONObject(3).getString("choice_text"));
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
