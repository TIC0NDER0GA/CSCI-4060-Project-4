package uga.edu.country_quiz;

import java.util.Random;

/**
 * A POJO to represent a Question and save the country and continents
 */
public class Question {

    private String country;
    private String rightC;
    private String wrongC1;
    private String wrongC2;

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

}
