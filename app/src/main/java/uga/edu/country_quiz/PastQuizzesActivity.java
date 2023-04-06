package uga.edu.country_quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * An activity that pulls all previous quizzes from the database
 * to be displayed with the date taken, score, and quiz number for the user.
 */
public class PastQuizzesActivity extends AppCompatActivity {

        private RecyclerView recyclerView; // used to hold all the views for the recycler view
        private LinearLayoutManager linearLayoutManager; // makes the recycler view scrollable and less memory intensive
        private DatabaseManager dbmanager; // used to manage and query any data needed from the database
        private QuizResultAdapter quizResultsAdapter; // adapts the raw data into views for use by the recycler view

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_past_quizzes);
            dbmanager = new DatabaseManager(this);
            recyclerView = (RecyclerView) findViewById(R.id.result_list);
            linearLayoutManager= new LinearLayoutManager(this);
            recyclerView.setLayoutManager(linearLayoutManager);
            quizResultsAdapter = new QuizResultAdapter(dbmanager.getAllQS());
            recyclerView.setAdapter(quizResultsAdapter);
        }
}