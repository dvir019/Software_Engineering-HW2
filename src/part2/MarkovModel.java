package part2;

import java.util.ArrayList;
import java.util.Random;

public class MarkovModel {
    private String myText;
    private Random myRandom;
    private int numOfChars;

    private static final int ONE = 1;

    public MarkovModel(int numOfChars) {
        this.numOfChars = numOfChars;
        myRandom = new Random();
    }

    public void setSeed(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String s) {
        myText = s.trim();
    }

    public String getRandomText(int numChars) {
        if (myText == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();

        int index = myRandom.nextInt(myText.length() - numOfChars);
        String key = myText.substring(index, index + numOfChars);
        sb.append(key);

        for (int k = numOfChars; k < numChars; k++) {
            ArrayList<Character> follows = getFollows(key);
            // TODO change
            if (follows.isEmpty()) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            char selectedChar = follows.get(index);
            key = key.substring(ONE, numOfChars) + selectedChar;
            sb.append(selectedChar);
        }

        return sb.toString();
    }

    public ArrayList<Character> getFollows(String key) {
        ArrayList<Character> follows = new ArrayList<>();
        int startIndex = myText.indexOf(key);

        while (startIndex != -1) {
            int followingCharIndex = startIndex + key.length();
            if (followingCharIndex < myText.length()) {
                follows.add(myText.charAt(followingCharIndex));
            }
            startIndex = myText.indexOf(key, startIndex + ONE);
        }
        return follows;
    }
}