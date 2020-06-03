package part2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class EfficientMarkovModel extends AbstractMarkovModel {

    private int numOfChars;
    private HashMap<String, ArrayList<Character>> hashMap;

    private static final int ONE = 1;

    public EfficientMarkovModel(int numOfChars) {
        super(numOfChars);
        this.numOfChars = numOfChars;
        myRandom = new Random();
        hashMap = new HashMap<>();
    }

    private void buildMap() {
        for (int startIndex = 0; startIndex + numOfChars <= myText.length(); startIndex++) {
            int followsCharIndex = startIndex + numOfChars;
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
    protected ArrayList<Character> getFollows(String key){
        return hashMap.get(key);
    }

    @Override
    public String toString() {
        return "EfficientMarkovModel of order " + numOfChars;
    }
}