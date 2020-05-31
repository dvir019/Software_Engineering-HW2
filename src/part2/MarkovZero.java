package part2;

import java.util.Random;

public class MarkovZero extends AbstractMarkovModel {

    private static final int ZERO = 0;

    public MarkovZero() {
        super(ZERO);
    }

    @Override
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