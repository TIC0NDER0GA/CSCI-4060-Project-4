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

/**
 * Activity to begin a new quiz after the user has
 * clicked the start button. Uses a CSVReader and
 * a DatabaseManager to read from the CSV and then
 * set up the list of questions to use for the quiz.
 */
public class StartQuizActivity extends AppCompatActivity {

    private Context context;
    private DatabaseManager manager; // will help the quiz array check for correct answer via database and will save score upon completion
    private CSVReader reader; // will read in all files from the initial CSV
    private QuestionPagerAdapter questionPagerAdapter;
    private ViewPager pager;
    private ArrayList<Question> quiz;
    private ArrayList<String> countriesList;
    private String[][] worldData;
    private int pos;
    private FragmentManager fragmentManager;

    /**
     * Creates the start quiz activity
     * Populates the quiz (question arrayList)
     * @param savedInstanceState If the fragment is being re-created from
     *      * a previous saved state, this is the state.
     */
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
        manager.insertCC(worldData);

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