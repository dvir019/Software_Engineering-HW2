package part2;

import java.util.ArrayList;

/** Generates random text basing on the last numOfChars chosen characters */
public class MarkovModel extends AbstractMarkovModel {
    // Fields
    private int numOfChars;

    // Constants
    private static final int ONE = 1;

    /**
     * Calls the base constructor and initializes numOfChars
     *
     * @param numOfChars numOfChars The number of characters to consider when choosing the next character
     */
    public MarkovModel(int numOfChars) {
        super(numOfChars);
        this.numOfChars = numOfChars;
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
            if (follows.isEmpty()) {  // There are no following characters
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