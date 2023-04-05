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

public class QuizResultAdapter extends RecyclerView.Adapter<QuizResultAdapter.ResultHolder> {

    private ArrayList<Quiz> results;

    public QuizResultAdapter(ArrayList<Quiz> quizzes) {
        results = quizzes;
    }


    @NonNull
    @Override
    public ResultHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View resultView = LayoutInflater.from(parent.getContext()).inflate(R.layout.past_quiz_items, parent, false);
        return new ResultHolder(resultView);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultHolder holder, int position) {
        holder.bind(results.get(position));
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public static class ResultHolder extends RecyclerView.ViewHolder {
        private TextView quizNum;
        private TextView quizDate;
        private TextView quizScore;

        public ResultHolder(@NonNull View itemView) {
            super(itemView);
            quizNum = itemView.findViewById(R.id.quiz_id);
            quizDate = itemView.findViewById(R.id.quiz_date);
            quizScore = itemView.findViewById(R.id.quiz_score);
        }

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
