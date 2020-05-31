package part2;

import java.util.ArrayList;
import java.util.Random;

public class MarkovOne extends AbstractMarkovModel{
    private String myText;
    private Random myRandom;


    public MarkovOne() {
        myRandom = new Random();
    }

    public void setSeed(int seed) {
        myRandom = new Random(seed);
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
}