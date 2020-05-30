package part1;

import java.util.ArrayList;
import java.util.Random;

public class Tester {
    private static void testGetFollows(){
        MarkovOne markovOne = new MarkovOne();
        String training = "this is a test yes this is a test.";
        markovOne.setTraining(training);

        ArrayList<Character> follows = markovOne.getFollows("t");
        System.out.println(follows);
    }

    public static void main(String[] args) {
//        testGetFollows();
        //System.out.println("abcd".indexOf("", 60));
        ArrayList<Character> l = new ArrayList<>();

    }
}
