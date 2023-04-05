package uga.edu.country_quiz;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
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
    private ArrayList<Question> quiz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbmanager = new DatabaseManager(this);
        intent = getIntent();
        String dateString = intent.getStringExtra("date");
        double percentage = intent.getDoubleExtra("score", 0.0);
        quiz = intent.getParcelableArrayListExtra("questions");
        // Log.d(TAG, dateString);
        // Log.d(TAG, String.valueOf(percentage));

        if (dateString != null) {
            dbmanager.insertQS(dateString,percentage);
        }

        setContentView(R.layout.activity_show_results);
        TextView scoreTextView = findViewById(R.id.textView5);
        scoreTextView.setText(Double.toString(percentage));

        ImageView image1 = findViewById(R.id.imageView1);
        TextView qId1 = findViewById(R.id.Qid1);
//        TextView choice1 = findViewById(R.id.countryAnswer1);
        TextView correct1 = findViewById(R.id.correctAnswer1);
        showQResult(image1, qId1,correct1, quiz.get(0), "Q1)");

//        recyclerView = (RecyclerView) findViewById(R.id.result_list);
//        linearLayoutManager= new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        quizResultsAdapter = new QuizResultAdapter(dbmanager.getAllQS());
//        recyclerView.setAdapter(quizResultsAdapter);
    }

    private void showQResult(ImageView image, TextView qIdView, TextView correct, Question currQ, String qNum) {
        correct.setText("Country: " + currQ.getCountry() + " Correct Answer:" + currQ.getRightC());
        qIdView.setText(qNum);
        if (currQ.isCorrect()) {
//            correct.setVisibility(correct.GONE);
            image.setImageResource(R.drawable.checkmark);
        }
        else {
//            correct.setText("Correct Answer: " + currQ.getRightC());
            image.setImageResource(R.drawable.wrong);
        }
    }
}
