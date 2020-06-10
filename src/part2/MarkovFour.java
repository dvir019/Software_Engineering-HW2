package part2;

import java.util.ArrayList;

/** Generates random text basing on the last four chosen characters */
public class MarkovFour extends AbstractMarkovModel {

    private static final int FOUR = 4;
    private static final int ONE = 1;

    public MarkovFour() {
        super(FOUR);
    }


    @Override
    public String getRandomText(int numChars) {
        if (myText == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();

        int index = myRandom.nextInt(myText.length() - FOUR);
        String key = myText.substring(index, index + FOUR);
        sb.append(key);

        for (int k = FOUR; k < numChars; k++) {
            ArrayList<Character> follows = getFollows(key);
            if (follows.isEmpty()) {  // There are no following characters
                break;
            }
            index = myRandom.nextInt(follows.size());
            char selectedChar = follows.get(index);
            key = key.substring(ONE, FOUR) + selectedChar;
            sb.append(selectedChar);
        }

        return sb.toString();
    }
}