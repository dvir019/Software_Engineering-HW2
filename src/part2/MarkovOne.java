package part2;

import java.util.ArrayList;

/** Generates random text basing on the last chosen character */
public class MarkovOne extends AbstractMarkovModel {
    // Constants
    private static final int ONE = 1;

    public MarkovOne() {
        super(ONE);
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
            if (follows.isEmpty()) {  // There are no following characters
                break;
            }
            index = myRandom.nextInt(follows.size());
            selectedChar = follows.get(index);
            sb.append(selectedChar);
        }

        return sb.toString();
    }
}