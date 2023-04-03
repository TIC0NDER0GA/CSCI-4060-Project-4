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

    private int questionNumber;
    private TextView questionView;
    private RadioGroup choices;
    private RadioButton choiceOne;
    private RadioButton choiceTwo;
    private RadioButton choiceThree;
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

        questionView.setText(question.getCountry());
        choiceOne.setText(question.getRightC());
        choiceTwo.setText(question.getWrongC1());
        choiceThree.setText(question.getWrongC2());
        return ui;
    }

    public String getChoice() {
        int choiceId = choices.getCheckedRadioButtonId();
        RadioButton selected = getView().findViewById(choiceId);
        return selected != null ? selected.getText().toString() : null;
    }
}