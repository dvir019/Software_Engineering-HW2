package part1;

import java.util.ArrayList;
import java.util.Random;

public class MarkovFour {
    private String myText;
    private Random myRandom;

    private static final int FOUR = 4;

    public MarkovFour() {
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

        int index = myRandom.nextInt(myText.length() - FOUR);
        String selectedString = myText.substring(index, index + FOUR);
        sb.append(selectedString);

        for (int k = 1; k < numChars; k++) {
            ArrayList<String> follows = getFollows(String.valueOf(selectedString));
            // TODO change
            if (follows.isEmpty()) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            selectedString = follows.get(index);
            System.out.println("Selected (" + selectedString + ")--------index="+index);
            sb.append(selectedString);
        }

        return sb.toString();
    }

    public ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<>();
        int startIndex = myText.indexOf(key);
        System.out.println("index="+startIndex);

        while (startIndex != -1) {
            int followingCharIndex = startIndex + key.length();
            if (followingCharIndex <= myText.length() - FOUR) {
                follows.add(myText.substring(followingCharIndex, followingCharIndex + FOUR));
            }
            startIndex = myText.indexOf(key, startIndex + 1);
        }
        System.out.print("follows: ");
        System.out.println(follows);

        return follows;
    }
}