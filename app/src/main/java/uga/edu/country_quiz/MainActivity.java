package uga.edu.country_quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button startButton;
    private Button pastResultsButton;
    private TextView descriptionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.button1);
        pastResultsButton = findViewById(R.id.button2);

        descriptionTextView = findViewById(R.id.textView2);
        descriptionTextView.setText("Use this app to test your geography knowledge!\n\n " +
                "First click the start quiz button. Then you will have 6 questions. For each " +
                "question choose the correct continent for that country. Swipe left to go to the " +
                "next question. If you want to see the results of your past quizzes click the View " +
                "Past Quizzes button.");

        CSVReader reader = new CSVReader(this);

        startButton.setOnClickListener(new ButtonClickListener());
        pastResultsButton.setOnClickListener(new ButtonClickListener());
    }

    /**
     * Class which acts as a listener for both the Start Quiz and View Past Quizzes buttons.
     * Starts the correct activity based on which button is clicked
     */
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            switch(view.getId()) {
                case R.id.button1:
                    intent = new Intent(view.getContext(), StartQuizActivity.class);
                    break;
                case R.id.button2:
                    intent = new Intent(view.getContext(), PastQuizzesActivity.class);
                    break;
            }
            startActivity(intent);
        }
    }
}