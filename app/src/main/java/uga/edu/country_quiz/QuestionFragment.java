package uga.edu.country_quiz;


import static android.content.ContentValues.TAG;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuestionFragment} factory method to
 * create an instance of this fragment.
 */
public class QuestionFragment extends Fragment {

    private int questionNumber;
    private TextView questionView;
    private RadioGroup choices;
    private RadioButton choiceOne;
    private RadioButton choiceTwo;
    private RadioButton choiceThree;
    private Button finishButton;
    private Question question;
    private ArrayList<Question> quiz;

    public QuestionFragment(ArrayList<Question> q) {
        quiz = q;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            questionNumber = getArguments().getInt("questionNumber");
            question = getArguments().getParcelable("question");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View ui = inflater.inflate(R.layout.fragment_question, container, false);
        choices = ui.findViewById(R.id.choices);
        choiceOne = ui.findViewById(R.id.choice_one);
        choiceTwo = ui.findViewById(R.id.choice_two);
        choiceThree = ui.findViewById(R.id.choice_three);
        questionView = ui.findViewById(R.id.country_choice);
        finishButton = ui.findViewById(R.id.finish_button);

        questionView.setText(question.getCountry());
        ArrayList<String> answerChoices = new ArrayList<String>();
        answerChoices.add(question.getRightC());
        answerChoices.add(question.getWrongC1());
        answerChoices.add(question.getWrongC2());

        Collections.shuffle(answerChoices);

        choiceOne.setText(answerChoices.get(0));
        choiceTwo.setText(answerChoices.get(1));
        choiceThree.setText(answerChoices.get(2));

//        choiceOne.setText(question.getRightC());
//        choiceTwo.setText(question.getWrongC1());
//        choiceThree.setText(question.getWrongC2());

        choiceOne.setOnClickListener(new QuestionFragment.ButtonClickListener());
        choiceTwo.setOnClickListener(new QuestionFragment.ButtonClickListener());
        choiceThree.setOnClickListener(new QuestionFragment.ButtonClickListener());
        finishButton.setOnClickListener(new QuestionFragment.ButtonClickListener());

        if (question.getQuestionNumber() == 5) {
            finishButton.setOnClickListener(new QuestionFragment.ButtonClickListener());
        }
        else {
            finishButton.setVisibility(finishButton.GONE);
        }

        return ui;
    }


    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            RadioButton choice;
            switch (view.getId()){
                case R.id.choice_one:
                    choice = (RadioButton) view;
                    question.setAnswerChoice(choice.getText().toString());
                    question.checkAnswer(choice.getText().toString());
                    quiz.get(question.getQuestionNumber()).checkAnswer(choice.getText().toString());
                    Log.e(TAG, choice.getText().toString()  + " Choice 1");

                    break;
                case R.id.choice_two:
                    choice = (RadioButton) view;
                    question.setAnswerChoice(choice.getText().toString());
                    question.checkAnswer(choice.getText().toString());
                    quiz.get(question.getQuestionNumber()).checkAnswer(choice.getText().toString());
                    Log.e(TAG, choice.getText().toString() + " Choice 2");
                    break;
                case R.id.choice_three:
                    choice = (RadioButton) view;
                    question.setAnswerChoice(choice.getText().toString());
                    question.checkAnswer(choice.getText().toString());
                    quiz.get(question.getQuestionNumber()).checkAnswer(choice.getText().toString());
                    Log.e(TAG, choice.getText().toString() +  " Choice 3");
                    break;
                case R.id.finish_button:
                    if (question.getQuestionNumber() == 5) {
                        Intent intent = new Intent(view.getContext(), ShowResultsActivity.class);
                        double correct = 0.0;
                        double total = (double) quiz.size();
                        double percentage = 0.0;
                        Date currentDate = new Date();
                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                        String dateString = formatter.format(currentDate);

                        for (int i = 0;  i < quiz.size(); i++) {
                            if (quiz.get(i).isCorrect()) {
                                correct++;
                            }
                        }
                        percentage = (correct / total) * 100;
                        // Log.e(TAG, "correct: " + correct + " /" + "total: " + total + " /" + "%: " + percentage);
                        // Log.e(TAG, dateString);
                        intent.putExtra("date", dateString);
                        intent.putExtra("score", percentage);
                        intent.putExtra("questions", quiz);
                        startActivity(intent);
                    }
            }
        }
    }

}