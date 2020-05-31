package part2;

import java.util.ArrayList;
import java.util.Random;

public class MarkovOne implements IMarkovModel{
    private String myText;
    private Random myRandom;


    public MarkovOne() {
        myRandom = new Random();
    }

    public void setSeed(int seed) {
        myRandom = new Random(seed);
    }

    @Override
    public void setTraining(String s) {
        myText = s.trim();
    }

    @Override
    public String getRandomText(int numChars) {
        if (myText == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();

        int index = myRandom.nextInt(myText.length() - 1);
        char selectedChar = myText.charAt(index);
        sb.append(selectedChar);

        for (int k = 1; k < numChars; k++) {
            ArrayList<Character> follows = getFollows(String.valueOf(selectedChar));
            // TODO change
            if (follows.isEmpty()) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            selectedChar = follows.get(index);
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
            startIndex = myText.indexOf(key, startIndex + 1);
        }
        return follows;
    }
}