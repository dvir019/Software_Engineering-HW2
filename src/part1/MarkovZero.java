package part1;

import java.util.Random;

/** Generates random text without basing on previous characters */
public class MarkovZero {
    private String myText;
    private Random myRandom;

    /** Initializes myRandom */
    public MarkovZero() {
        myRandom = new Random();
    }

    public void setSeed(int seed) {
        myRandom = new Random(seed);
    }

    /**
     * Sets myText to the given string after trimming it  # TODO improve
     *
     * @param s The new training text
     */
    public void setTraining(String s) {
        myText = s.trim();
    }

    /**
     * Chooses random text contains numChars characters
     *
     * Chooses randomly a given number of characters from the training text,
     * and returns the chosen characters as a string.
     *
     * @param numChars The number of characters to choose from the text
     * @return The randomly chosen characters
     */
    public String getRandomText(int numChars) {
        if (myText == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int k = 0; k < numChars; k++) {
            int index = myRandom.nextInt(myText.length());
            sb.append(myText.charAt(index));
        }

        return sb.toString();
    }
}