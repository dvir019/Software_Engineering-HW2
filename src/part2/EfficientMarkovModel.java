package part2;

import java.util.ArrayList;
import java.util.HashMap;


public class EfficientMarkovModel extends AbstractMarkovModel {

    private HashMap<String, ArrayList<Character>> hashMap;

    private static final int ONE = 1;

    /**
     * Calls the base constructor and creates the hashMap object
     *
     * @param numOfChars numOfChars The number of characters to consider when choosing the next character
     */
    public EfficientMarkovModel(int numOfChars) {
        super(numOfChars);
        hashMap = new HashMap<>();
    }

    /**
     * Adds to the hashMap all following characters to all of the sequences in the text
     * <p>
     * Iterates over the training text, and adds to the hashMap object all of following
     * characters to all of the sequences which their length equals markovOrder in the text.
     */
    private void buildMap() {
        // Clear the hashMap object
        hashMap.clear();

        // Iterate over the training text and add th the hashMap object
        for (int startIndex = 0; startIndex + markovOrder <= myText.length(); startIndex++) {
            int followsCharIndex = startIndex + markovOrder;
            String key = myText.substring(startIndex, followsCharIndex);
            boolean keyInHashMap = hashMap.containsKey(key);
            if (followsCharIndex == myText.length()) {
                if (!keyInHashMap) {
                    hashMap.put(key, new ArrayList<>());
                }
            } else {
                char followsChar = myText.charAt(followsCharIndex);
                if (keyInHashMap) {
                    hashMap.get(key).add(followsChar);
                } else {
                    ArrayList<Character> followsList = new ArrayList<>();
                    followsList.add(followsChar);
                    hashMap.put(key, followsList);
                }
            }
        }
    }

    /** Sets the training text, and builds the hashMap object according to it */
    @Override
    public void setTraining(String s) {
        super.setTraining(s);
        buildMap();
    }

    @Override
    public String getRandomText(int numChars) {
        if (myText == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();

        int index = myRandom.nextInt(myText.length() - markovOrder);
        String key = myText.substring(index, index + markovOrder);
        sb.append(key);

        for (int k = markovOrder; k < numChars; k++) {
            ArrayList<Character> follows = getFollows(key);
            // TODO change
            if (follows.isEmpty()) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            char selectedChar = follows.get(index);
            key = key.substring(ONE, markovOrder) + selectedChar;
            sb.append(selectedChar);
        }

        return sb.toString();
    }


    @Override
    protected ArrayList<Character> getFollows(String key) {
        return hashMap.get(key);  // TODO Check if doesn't exist
    }

    @Override
    public String toString() {
        return "EfficientMarkovModel of order " + markovOrder;
    }
}