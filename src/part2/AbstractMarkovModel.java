package part2;

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    private int markovOrder;

    public AbstractMarkovModel(int markovOrder) {
        this.markovOrder = markovOrder;
        myRandom = new Random();
    }

    final public void setTraining(String s) {
        myText = s.trim();
    }

    abstract public String getRandomText(int numChars);

    protected ArrayList<Character> getFollows(String key) {
        ArrayList<Character> follows = new ArrayList<>();
        int startIndex = myText.indexOf(key);

        while (startIndex != -1) {
            int followingCharIndex = startIndex + key.length();
            if (followingCharIndex < myText.length()) {
                follows.add(myText.charAt(followingCharIndex));
            }
            startIndex = myText.indexOf(key, startIndex + 1);
        }
        return follows;
    }

    @Override
    public String toString() {
        return "MarkovModel of order " + markovOrder;
    }
}
