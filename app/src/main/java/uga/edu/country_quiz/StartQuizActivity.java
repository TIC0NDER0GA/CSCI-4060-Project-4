package uga.edu.country_quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_quiz);
        int limit = 7;
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
            memberCountry = reader.getCountry(reader.getRandIndex());
            memberContinent = reader.getContinent(reader.getRandIndex());
            if (quiz.isEmpty() || !(countriesList.contains(memberCountry))) {
                countriesList.add(memberCountry);
                quiz.add(new Question(memberCountry,memberContinent));
                added++;
            }
        }

        pager = findViewById(R.id.viewPager);
        questionPagerAdapter = new QuestionPagerAdapter(this, quiz, getSupportFragmentManager());
        pager.setAdapter(questionPagerAdapter);

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                                          @Override
                                          public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                                          }
                                          @Override
                                          public void onPageSelected(int position) {
                                              pos = position;
                                          }
                                          @Override
                                          public void onPageScrollStateChanged(int state) {

                                          }
                                      }
        );
    }
}