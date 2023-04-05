package uga.edu.country_quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;


public class PastQuizzesActivity extends AppCompatActivity {

        private ArrayList<Quiz> results;
        private RecyclerView recyclerView;
        private LinearLayoutManager linearLayoutManager;
        private DatabaseManager dbmanager;
        private QuizResultAdapter quizResultsAdapter;
        private Intent intent;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            dbmanager = new DatabaseManager(this);
            intent = getIntent();
            String dateString = intent.getStringExtra("date");
            double percentage = intent.getDoubleExtra("score", 0.0);
            // Log.d(TAG, dateString);
            // Log.d(TAG, String.valueOf(percentage));

            if (dateString != null) {
                dbmanager.insertQS(dateString,percentage);
            }

            setContentView(R.layout.activity_past_quizzes);
            recyclerView = (RecyclerView) findViewById(R.id.result_list);
            linearLayoutManager= new LinearLayoutManager(this);
            recyclerView.setLayoutManager(linearLayoutManager);
            quizResultsAdapter = new QuizResultAdapter(dbmanager.getAllQS());
            recyclerView.setAdapter(quizResultsAdapter);
        }
}