package part2;

import java.util.ArrayList;
import java.util.Random;

public class MarkovModel extends AbstractMarkovModel {

    private int numOfChars;

    private static final int ONE = 1;

    public MarkovModel(int numOfChars) {
        super(numOfChars);
        this.numOfChars = numOfChars;
        myRandom = new Random();
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
}