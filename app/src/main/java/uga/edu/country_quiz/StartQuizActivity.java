package uga.edu.country_quiz;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class StartQuizActivity extends AppCompatActivity {

    private Context context;
    private DatabaseManager manager; // will help the quiz array check for corect answer via database and will save score upon completion
    private CSVReader reader; // will read in all files from the initial CSV
    private QuestionPagerAdapter questionPagerAdapter;
    private ViewPager pager;
    private ArrayList<Question> quiz;
    private ArrayList<String> countriesList;
    private String[][] worldData;
    private int pos;
    private FragmentManager fragmentManager;
    private RadioButton button_one;
    private RadioButton button_two;
    private RadioButton button_three;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_quiz);
        int limit = 6;
        int added = 0;
        pos = 0;
        String memberCountry = "";
        String memberContinent = "";
        fragmentManager = getSupportFragmentManager();
        quiz = new ArrayList<Question>();
        countriesList =  new ArrayList<String>();
        context = getApplicationContext();
        reader = new CSVReader(context);
        manager = new DatabaseManager(context);
        worldData = reader.getData();
        for (int i = 0 ; i < worldData.length; i++) {
            manager.insertCC(worldData[i][0],worldData[i][1]);
        }

        while (added < limit) {
            int index = reader.getRandIndex();
            memberCountry = reader.getCountry(index);
            memberContinent = reader.getContinent(index);
            Log.e(TAG, memberCountry + ", " + memberContinent);
            if (quiz.isEmpty() || !(countriesList.contains(memberCountry))) {
                countriesList.add(memberCountry);
                Question newQ = new Question(memberCountry,memberContinent);
                newQ.setQuestionNumber(added);
                quiz.add(newQ);
                added++;
            }
        }

        pager = findViewById(R.id.viewPager);
        questionPagerAdapter = new QuestionPagerAdapter(this, quiz, getSupportFragmentManager());
        pager.setAdapter(questionPagerAdapter);

    }




}