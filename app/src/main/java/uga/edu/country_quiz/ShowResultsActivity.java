package uga.edu.country_quiz;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ShowResultsActivity extends AppCompatActivity {

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

        setContentView(R.layout.activity_show_results);
        recyclerView = (RecyclerView) findViewById(R.id.result_list);
        linearLayoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        quizResultsAdapter = new QuizResultAdapter(dbmanager.getAllQS());
        recyclerView.setAdapter(quizResultsAdapter);
        TextView scoreTextView = findViewById(R.id.textView5);
        scoreTextView.setText(Double.toString(percentage));
    }
}
