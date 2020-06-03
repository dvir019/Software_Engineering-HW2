package part1;

import java.util.ArrayList;

/** Tests the getFollows method found in MarkovOne class */
public class Tester {

    /** Tests the getFollows method found in MarkovOne class */
    private static void testGetFollows() {
        MarkovOne markovOne = new MarkovOne();
        String training = "this is a test yes this is a test.";
        markovOne.setTraining(training);

        ArrayList<Character> follows = markovOne.getFollows("t");
        System.out.println(follows);
    }

    /** Runs the test */
    public static void main(String[] args) {
        testGetFollows();
    }
}
