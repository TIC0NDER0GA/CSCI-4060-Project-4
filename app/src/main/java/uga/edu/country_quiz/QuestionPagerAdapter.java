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
    private ArrayList<Question> quiz; // holds all the quiz questions
    private FragmentManager fragmentManager; // manages the fragments

    /**
     * Initializes the Question Fragment
     * @param context the context from the parent activity
     * @param data the quiz questions
     * @param fg the support fragment manager from the parent activity
     */
    public QuestionPagerAdapter(Context context, ArrayList<Question> data, FragmentManager fg) {
        quiz = data;
        fragmentManager = fg;
    }

    /**
     * Gets how many questions are in the quiz.
     * @return the amount of questions in the quiz
     */
    @Override
    public int getCount() {
        return quiz.size();
    }

    /**
     * Checks if the view is from a parent object.
     * @param view Page View to check for association with <code>object</code>
     * @param object Object to check for association with <code>view</code>
     * @return boolean if the view is from an abject
     */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((Fragment) object).getView();
    }

    /**
     * The method that actually puts all parts of the question
     * into the question fragment.
     * @param container The containing View in which the page will be shown.
     * @param position The page position to be instantiated.
     * @return
     */
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

    /**
     * Method used to destroy the fragment when more resources are needed.
     * @param container The containing View from which the page will be removed.
     * @param position The page position to be removed.
     * @param object The same object that was returned by
     * {@link #instantiateItem(View, int)}.
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        fragmentManager.beginTransaction()
                .remove((Fragment) object)
                .commit();
    }
}
