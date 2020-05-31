package part2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class EfficientMarkovModel extends AbstractMarkovModel {

    private int numOfChars;
    HashMap<String, ArrayList<Character>> hashMap;

    private static final int ONE = 1;

    public EfficientMarkovModel(int numOfChars) {
        super(numOfChars);
        this.numOfChars = numOfChars;
        myRandom = new Random();
        hashMap = new HashMap<>();
    }

    private void buildMap() {

    }

    @Override
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

    @Override
    public String toString() {
        return "EfficientMarkovModel of order " + numOfChars;
    }
}