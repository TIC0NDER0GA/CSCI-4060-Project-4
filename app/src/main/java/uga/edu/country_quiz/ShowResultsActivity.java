package uga.edu.country_quiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Activity to show the results of the individual questions
 * from the quiz which was just taken.
 */
public class ShowResultsActivity extends AppCompatActivity {

    private DatabaseManager dbmanager;
    private Intent intent;
    private ArrayList<Question> quiz;

    private Button startQuiz;
    private Button viewPast;

    /**
     * Creates the show results activity, including all results
     * for the quiz questions
     * @param savedInstanceState If the fragment is being re-created from
     *      * a previous saved state, this is the state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_results);

        dbmanager = new DatabaseManager(this);
        intent = getIntent();
        String dateString = intent.getStringExtra("date");
        double percentage = intent.getDoubleExtra("score", 0.0);
        quiz = intent.getParcelableArrayListExtra("questions");

        if (dateString != null) {
            dbmanager.insertQS(dateString,percentage);
        }

        startQuiz = findViewById(R.id.startNew);
        viewPast = findViewById(R.id.viewPast);

        startQuiz.setOnClickListener(new ButtonClickListener());
        viewPast.setOnClickListener(new ButtonClickListener());

        TextView scoreTextView = findViewById(R.id.textView5);
        scoreTextView.setText(Integer.toString((int) percentage));

        ImageView image1 = findViewById(R.id.imageView1);
        TextView qId1 = findViewById(R.id.Qid1);
        TextView correct1 = findViewById(R.id.answer1);
        showQResult(image1, qId1, correct1, quiz.get(0), "Q1) ");

        ImageView image2 = findViewById(R.id.imageView2);
        TextView qId2 = findViewById(R.id.Qid2);
        TextView correct2 = findViewById(R.id.amswer2);
        showQResult(image2, qId2, correct2, quiz.get(1), "Q2) ");

        ImageView image3 = findViewById(R.id.imageView3);
        TextView qId3 = findViewById(R.id.Qid3);
        TextView correct3 = findViewById(R.id.answer3);
        showQResult(image3, qId3, correct3, quiz.get(2), "Q3) ");

        ImageView image4 = findViewById(R.id.imageView4);
        TextView qId4 = findViewById(R.id.Qid4);
        TextView correct4 = findViewById(R.id.answer4);
        showQResult(image4, qId4, correct4, quiz.get(3), "Q4) ");

        ImageView image5 = findViewById(R.id.imageView5);
        TextView qId5 = findViewById(R.id.Qid5);
        TextView correct5 = findViewById(R.id.answer5);
        showQResult(image5, qId5, correct5, quiz.get(4), "Q5) ");

        ImageView image6 = findViewById(R.id.imageView6);
        TextView qId6 = findViewById(R.id.Qid6);
        TextView correct6 = findViewById(R.id.answer6);
        showQResult(image6, qId6,correct6, quiz.get(5), "Q6) ");

    }

    /**
     * Sets the views to the correct question number,
     * country, & continent. Also shows a checkmark
     * for a correct answer and an X for a wrong one.
     * @param image View for checkmark or X to show correctness
     * @param qIdView View for question number and country name
     * @param correct View for correct continent
     * @param currQ Question to be displayed
     * @param qNum Question number
     */
    private void showQResult(ImageView image, TextView qIdView, TextView correct, Question currQ, String qNum) {
        correct.setText("Correct Answer:  " + currQ.getRightC());
        qIdView.setText(qNum + currQ.getCountry());
        if (currQ.isCorrect()) {
            image.setImageResource(R.drawable.checkmark);
        }
        else {
            image.setImageResource(R.drawable.wrong);
        }
    }

    /**
     * Listener for the "Start New Quiz" and "View Past Quizzes"
     * buttons and then starts the correct activity
     */
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            switch(view.getId()) {
                case R.id.startNew:
                    intent = new Intent(view.getContext(), StartQuizActivity.class);
                    break;
                case R.id.viewPast:
                    intent = new Intent(view.getContext(), PastQuizzesActivity.class);
                    break;
            }
            startActivity(intent);
        }
    }

}
