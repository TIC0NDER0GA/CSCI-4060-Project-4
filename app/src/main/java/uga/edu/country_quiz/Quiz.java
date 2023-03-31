package uga.edu.country_quiz;
import java.util.Random;

public class Quiz {

//    private Question q1;
//    private Question q2;
//    private Question q3;
//    private Question q4;
//    private Question q5;
//    private Question q6;

    private Question[] questions = new Question[6];

    private String dt; // date quiz was taken
    private double sr;  // score on quiz
    private int numQs;  // num questions answered so far

    public Quiz(String[][] data, int numCountries) {
        Random rand = new Random();
        int randNum = 0;
        for (int i = 0; i < 6; i++) {
            boolean duplicateFound = true;
            while (duplicateFound) {
                randNum = rand.nextInt(numCountries);
                for (int j = 0; j < data.length; j++) {
                    if (!data[randNum][0].equals(data[j][0])) {
                        duplicateFound = false;
                    }
                }
            }
            questions[i] = new Question(data[randNum][0], data[randNum][1]);

        }

    }
}
