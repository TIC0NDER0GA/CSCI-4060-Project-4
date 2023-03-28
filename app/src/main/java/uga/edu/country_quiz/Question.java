package uga.edu.country_quiz;

public class Question {

    private String country;

    private String rightC;

    private String wrongC1;

    private String wrongC2;

    public Question(String country, String rightC, String wrongC1,String wrongC2) {
        this.country = country;
        this.rightC = rightC;
        this.wrongC1 = wrongC1;
        this.wrongC2 = wrongC2;
    }
}
