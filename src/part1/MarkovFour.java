package part1;

import java.util.ArrayList;
import java.util.Random;

/** Generates random text basing on the last four chosen characters */
public class MarkovFour {
    // Fields
    private String myText;
    private Random myRandom;

    // Constants
    private static final int FOUR = 4;
    private static final int ONE = 1;

    /** Initializes myRandom */
    public MarkovFour() {
        myRandom = new Random();
    }

    public void setSeed(int seed) {
        myRandom = new Random(seed);
    }

    /**
     * Sets myText to the given string
     * <p>
     * Trims the leading and trailing whitespaces from the given string,
     * and sets myText to the trimmed string.
     *
     * @param text The new training text
     */
    public void setTraining(String text) {
        myText = text.trim();
    }

    /**
     * Chooses random text contains numChars characters
     * <p>
     * Chooses randomly a sequence of four character from the text, and then repeatedly choose one more
     * character from the list of characters that appear after the last chosen four characters.
     *
     * @param numChars The total number of characters to choose from the text
     * @return The chosen characters, as a string
     */
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

    /**
     * Gets a list of all characters which appears after the given key
     * <p>
     * Iterates over the training text and adds all of the characters that appear
     * directly after the given key into the follows list, and returns the list.
     * <p>
     * If there are no characters after the key, an empty list will be returned.
     *
     * @param key String represents the key
     * @return A list of all of the characters which appear directly after the key
     */
    public ArrayList<Character> getFollows(String key) {
        ArrayList<Character> follows = new ArrayList<>();
        int startIndex = myText.indexOf(key);

        while (startIndex != -ONE) {
            int followingCharIndex = startIndex + key.length();
            if (followingCharIndex < myText.length()) {
                follows.add(myText.charAt(followingCharIndex));
            }
            startIndex = myText.indexOf(key, startIndex + ONE);
        }
        return follows;
    }
}