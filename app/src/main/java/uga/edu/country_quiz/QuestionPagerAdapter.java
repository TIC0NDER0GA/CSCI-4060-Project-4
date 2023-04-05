package uga.edu.country_quiz;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class QuestionPagerAdapter extends PagerAdapter {
    private ArrayList<Question> quiz;
    private LayoutInflater layoutInflater;
    private int size;

    private FragmentManager fragmentManager;

    public QuestionPagerAdapter(Context context, ArrayList<Question> data, FragmentManager fg) {
        quiz = data;
        size = quiz.size();
        layoutInflater = LayoutInflater.from(context);
        fragmentManager = fg;
    }

    @Override
    public int getCount() {
        return quiz.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((Fragment) object).getView();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        QuestionFragment fragment = new QuestionFragment(quiz);
        Bundle args = new Bundle();
        args.putParcelable("question", quiz.get(position));
        fragment.setArguments(args);
        fragmentManager.beginTransaction()
                .add(container.getId(), fragment)
                .commit();
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        fragmentManager.beginTransaction()
                .remove((Fragment) object)
                .commit();
    }
}
