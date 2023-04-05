package uga.edu.country_quiz;

import static android.content.ContentValues.TAG;

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
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuestionFragment#newInstance} factory method to
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

    public QuestionFragment() {
        // Required empty public constructor
    }

    public static QuestionFragment newInstance(int questionNumber, Question question) {
        QuestionFragment fragment = new QuestionFragment();
        Bundle args = new Bundle();
        args.putInt("questionNumber", questionNumber);
        args.putParcelable("question", question);
        fragment.setArguments(args);
        return fragment;
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
        choiceOne.setText(question.getRightC());
        choiceTwo.setText(question.getWrongC1());
        choiceThree.setText(question.getWrongC2());

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

    public String getChoice() {
        int choiceId = choices.getCheckedRadioButtonId();
        RadioButton selected = getView().findViewById(choiceId);
        return selected != null ? selected.getText().toString() : null;
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
                case R.id.choice_two:
                    choice = (RadioButton) view;
                    question.setAnswerChoice(choice.getText().toString());
                    question.checkAnswer(choice.getText().toString());
                case R.id.choice_three:
                    choice = (RadioButton) view;
                    question.setAnswerChoice(choice.getText().toString());
                    question.checkAnswer(choice.getText().toString());
                case R.id.finish_button:
                    if (question.getQuestionNumber() == 5) {
                        Intent intent = new Intent(view.getContext(), ShowResultsActivity.class);
                        startActivity(intent);
                    }
            }
        }
    }

}