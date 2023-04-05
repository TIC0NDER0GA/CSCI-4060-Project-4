package uga.edu.country_quiz;


public class Quiz {

    private int quizNumber;
    private String dt; // date quiz was taken
    private double sr;  // score on quiz



    public Quiz() {

    }

    public Quiz(int quizNum, String date, double score) {
        quizNumber = quizNum;
        dt = date;
        sr = score;
    }

    public double getSr() {
        return sr;
    }

    public int getQuizNumber() {
        return quizNumber;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public void setQuizNumber(int quizNumber) {
        this.quizNumber = quizNumber;
    }

    public void setSr(double sr) {
        this.sr = sr;
    }
}
