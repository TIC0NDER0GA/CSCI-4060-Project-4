package uga.edu.country_quiz;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Random;

/**
 * A POJO to represent a Question and save the country and continents
 */
public class Question implements Parcelable {

    private String country; // the country used for the question
    private String rightC; // the correct continent for the country
    private String wrongC1; // the first wrong choice
    private String wrongC2; // the second wrong choice
    private boolean correct; // if this question was answered correctly
    private String answerChoice = ""; // stores user answer from radiobutto
    private int qNumber = 0; // the position of the question in the quiz


    /**
     * A method that sets all the values in order to make a question
     * parcable. This makes it easier to dynami
     * @param in
     */
    public Question(Parcel in) {
        country = in.readString();
        rightC = in.readString();
        wrongC1 = in.readString();
        wrongC2 = in.readString();
        correct = in.readByte() != 0;
    }


    /**
     * Required method to create a Parcelable object from the Question class
     */
    public static final Creator<Question> CREATOR = new Creator<Question>() {

        /**
         * Creates a Question obj
         * @param in the class vars for the Question obj
         * @return a Parcable Question obj
         */
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        /**
         * inits an array to hold the class vars
         * @param size amount of class vars
         * @return
         */
        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Necessary parcable stuff
     * @param dest where it's going
     * @param flags unused
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(country);
        dest.writeString(rightC);
        dest.writeString(wrongC1);
        dest.writeString(wrongC2);
        dest.writeByte((byte) (correct ? 1 : 0));
    }

    final private String[] continents = new String[] {"South America", "North America", "Asia",
            "Africa", "Oceania", "Europe"};

    /**
     * Constructor for a question instance
     * Randomly assigns the 2 incorrect continent choices
     * @param country the chosen country which is passed in from the quiz instance
     * @param rightC the correct continent, also passed in from the quiz instance
     */
    public Question(String country, String rightC) {
        this.country = country;
        this.rightC = rightC;

        Random rand = new Random();
        int i;
        while (wrongC1 == null) {
            i = rand.nextInt(6);
            if (!continents[i].equals(rightC)) {
                wrongC1 = continents[i];
            }
        }
        while (wrongC2 == null) {
            i = rand.nextInt(6);
            if (!continents[i].equals(rightC) && !continents[i].equals(wrongC1)) {
                wrongC2 = continents[i];
            }
        }
    }

    public void checkAnswer(String choice) { correct = choice.equals(rightC); }

    public String getRightC() {
        return rightC;
    }

    public String getWrongC1() {
        return wrongC1;
    }

    public String getWrongC2() {
        return wrongC2;
    }

    public String getCountry() {
        return country;
    }

    public boolean isCorrect() { return correct; }

    public void setAnswerChoice(String selectedAnswer) {
        this.answerChoice =  selectedAnswer;
    }

    public String getAnswerChoice() {return this.answerChoice;}

    public int getQuestionNumber() { return qNumber; }

    public void setQuestionNumber(int qNumber) {
        this.qNumber = qNumber;
    }



}
