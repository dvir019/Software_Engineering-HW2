package part1;

import util.*;

public class MarkovRunner {

    public void runMarkovZero(String trainingFilePath) {
        SEFileUtil seFileUtil = new SEFileUtil(trainingFilePath);
        String st = seFileUtil.asString();
        st = st.replace('\n', ' ');
        MarkovZero markov = new MarkovZero();
        markov.setSeed(101);
        markov.setTraining(st);
        for (int k = 0; k < 3; k++) {
            String text = markov.getRandomText(500);
            printOut(text);
        }
    }

    private void printOut(String s) {
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for (int k = 0; k < words.length; k++) {
            System.out.print(words[k] + " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println();
                psize = 0;
            }
        }
        System.out.println("\n----------------------------------");
    }

    public static void main(String[] args) {
        MarkovRunner markovRunner = new MarkovRunner();
//      markovRunner.runMarkovZero(args[0]);
        String path = "C:\\Users\\Dvir\\Desktop\\Software_Engineering\\HW2\\Software_Engineering-HW2\\Data\\alice.txt";
        markovRunner.runMarkovZero(path);
    }

}
