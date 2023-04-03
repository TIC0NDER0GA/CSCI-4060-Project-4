package uga.edu.country_quiz;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuestionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuestionFragment extends Fragment {

    private TextView country; // country the user must guess continent for
    private RadioGroup choices;
    private RadioButton choice_one; // will work with the Quiz class to determine user choice and correctness
    private RadioButton choice_two;
    private RadioButton choice_three;
    private int numOfQuestions;
    private Question question;

    public QuestionFragment() {
        // Required empty public constructor
    }

    public QuestionFragment(Question q) {
        question = q;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View ui = inflater.inflate(R.layout.fragment_question, container, false);
        choices = (RadioGroup) ui.findViewById(R.id.choices);
        choice_one = (RadioButton) ui.findViewById(R.id.choice_one);
        choice_two = (RadioButton) ui.findViewById(R.id.choice_two);
        choice_three = (RadioButton) ui.findViewById(R.id.choice_three);
        country = (TextView) ui.findViewById(R.id.country_choice);

        country.setText(question.getCountry());
        choice_one.setText(question.getRightC());
        choice_two.setText(question.getWrongC1());
        choice_three.setText(question.getWrongC2());
        return ui;
    }


    public String getChoice(int i) {
        switch(i) {
            case 0:
                return choice_one.getText().toString();
            case 1:
                return choice_two.getText().toString();
            case 2:
                return choice_three.getText().toString();
            default:
                return "NOT A VALID CHOICE";
        }

    }
}