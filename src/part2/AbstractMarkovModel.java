package part2;

import java.util.*;

/** Abstract class which generates random text basing on the last markovOrder chosen characters */
public abstract class AbstractMarkovModel implements IMarkovModel {
    // Fields
    protected String myText;
    protected Random myRandom;
    protected int markovOrder;

    // Constants
    private static final int ONE = 1;

    /**
     * Sets markovOrder and initializes myRandom
     *
     * @param markovOrder The number of characters to consider when choosing the next character
     */
    public AbstractMarkovModel(int markovOrder) {
        this.markovOrder = markovOrder;
        myRandom = new Random();
    }

    @Override
    public void setTraining(String s) {
        myText = s.trim();
    }

    @Override
    final public void setSeed(int seed) {
        myRandom.setSeed(seed);
    }

    abstract public String getRandomText(int numChars);

    /**
     * Gets a list of all characters which appears after the given key
     * <p>
     * Iterates over the training text and adds all of the characters that appear
     * directly after the given key into the follows list, and returns the list.
     * <p>
     * If there are no characters after the key, an empty list will be returned.
     *
     * @param key The # TODO add description
     * @return A list of all of the characters which appear directly after the key
     */
    protected ArrayList<Character> getFollows(String key) {
        ArrayList<Character> follows = new ArrayList<>();
        int startIndex = myText.indexOf(key);

        while (startIndex != -ONE) {
            int followingCharIndex = startIndex + key.length();
            if (followingCharIndex < myText.length()) {
                follows.add(myText.charAt(followingCharIndex));
            }
            startIndex = myText.indexOf(key, startIndex + ONE);
        }
        return follows;
    }

    @Override
    public String toString() {
        return "MarkovModel of order " + markovOrder;
    }
}
