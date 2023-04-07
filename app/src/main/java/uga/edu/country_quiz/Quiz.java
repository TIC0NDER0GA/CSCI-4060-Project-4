package uga.edu.country_quiz;

/**
 * A quiz object to store the date and score of each quiz
 */
public class Quiz {

    private int quizNumber;
    private String dt; // date quiz was taken
    private double sr;  // score on quiz

    /**
     * Creates a quiz instance
     * @param quizNum The quiz ID
     * @param date The date the quiz was taken
     * @param score The score that the user receives on the quiz
     */
    public Quiz(int quizNum, String date, double score) {
        quizNumber = quizNum;
        dt = date;
        sr = score;
    }

    /**
     * A method that returns the score of the quiz
     * @return A double which is the score
     */
    public double getSr() {
        return sr;
    }

    /**
     * A method that returns the quiz number (ID)
     * @return An integer which is the ID
     */
    public int getQuizNumber() {
        return quizNumber;
    }

    /**
     * A method that returns the date the quiz was taken
     * @return A string which is the date
     */
    public String getDt() {
        return dt;
    }

    /**
     * A method to set the date that the quiz was taken
     * @param dt A string which represents the date
     */
    public void setDt(String dt) {
        this.dt = dt;
    }

    /**
     * A method to set the ID of the quiz
     * @param quizNumber An integer which is the quiz ID
     */
    public void setQuizNumber(int quizNumber) {
        this.quizNumber = quizNumber;
    }

    /**
     * A method to set the score of the quiz
     * @param sr A double which is the score
     */
    public void setSr(double sr) {
        this.sr = sr;
    }
}
