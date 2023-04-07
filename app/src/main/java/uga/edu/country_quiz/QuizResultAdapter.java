package uga.edu.country_quiz;

import static android.content.ContentValues.TAG;

import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Class that implements the recycler view Adpater
 * to properly input all previous quizzes into the
 * Fragment list.
 */
public class QuizResultAdapter extends RecyclerView.Adapter<QuizResultAdapter.ResultHolder> {

    private ArrayList<Quiz> results; // the previous quiz results

    /**
     * Inits the QuizResultAdapter.
     * @param quizzes the past quiz result data
     */
    public QuizResultAdapter(ArrayList<Quiz> quizzes) {
        results = quizzes;
    }


    /**
     * Makes the holder with the actual
     * constructed view.
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     * an adapter position.
     * @param viewType The view type of the new View.
     * @return the view that hold will hold the data
     */
    @NonNull
    @Override
    public ResultHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View resultView = LayoutInflater.from(parent.getContext()).inflate(R.layout.past_quiz_items, parent, false);
        return new ResultHolder(resultView);
    }


    /**
     * Binds the data into the view from the specified position of the quiz obj.
     * @param holder The ViewHolder which should be updated to represent the contents of the
     * item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull ResultHolder holder, int position) {
        holder.bind(results.get(position));
    }

    /**
     * Gets the number of items for use by the RecyclerView.
     * @return the size of the ArrayList<Quiz>
     */
    @Override
    public int getItemCount() {
        return results.size();
    }

    /**
     * A class that handles the job of getting the
     * views from the XML layout and binds each quiz result
     * one by one into the list.
     */
    public static class ResultHolder extends RecyclerView.ViewHolder {
        private TextView quizNum; // refers to the attempt number
        private TextView quizDate; // when the user took the quiz
        private TextView quizScore; // the score out of 100.00%

        /**
         * Gets the id's of the TextViews used in the fragment.
         * @param itemView
         */
        public ResultHolder(@NonNull View itemView) {
            super(itemView);
            quizNum = itemView.findViewById(R.id.quiz_id);
            quizDate = itemView.findViewById(R.id.quiz_date);
            quizScore = itemView.findViewById(R.id.quiz_score);
        }

        /**
         * Uses the quiz put into as a parameter
         * specified by the position and binds it's data
         * into a view.
         * @param qItem the singular quiz item that needs to be bound.
         */
        public void bind(Quiz qItem) {

            Log.d(TAG, String.valueOf(qItem.getQuizNumber()));
            Log.d(TAG, qItem.getDt());
            Log.d(TAG,String.valueOf(qItem.getSr()));

            quizNum.setText(String.valueOf(qItem.getQuizNumber()));
            quizDate.setText(qItem.getDt());
            quizScore.setText(String.valueOf(qItem.getSr()));
        }
    }
}
