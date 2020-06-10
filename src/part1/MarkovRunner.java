package part1;

import util.*;

/**
 * Execute tests of the Markov classes
 * <p>
 * MarkovZero, MarkovOne, MarkovFour and MarkovModel are being tested,
 * where MarkovModel is tested with numOfChars=6.
 */
public class MarkovRunner {
    /**
     * Generates three random texts using MarkovZero.
     * <p>
     * Uses MarkovZero class to generate three random texts, which contains
     * five hundred characters each, and prints the generated texts.
     *
     * @param trainingFilePath full path to the text file
     */
    public void runMarkovZero(String trainingFilePath) {
        SEFileUtil seFileUtil = new SEFileUtil(trainingFilePath);
        String st = seFileUtil.asString();
        st = st.replace('\n', ' ');
        MarkovZero markov = new MarkovZero();
        markov.setSeed(25);
        markov.setTraining(st);
        for (int k = 0; k < 3; k++) {
            String text = markov.getRandomText(500);
            printOut(text);
        }
    }

    /**
     * Generates three random texts using MarkovOne.
     * <p>
     * Uses MarkovOne class to generate three random texts, which contains
     * five hundred characters each, and prints the generated texts.
     *
     * @param trainingFilePath full path to the text file
     */
    public void runMarkovOne(String trainingFilePath) {
        SEFileUtil seFileUtil = new SEFileUtil(trainingFilePath);
        String st = seFileUtil.asString();
        st = st.replace('\n', ' ');
        MarkovOne markov = new MarkovOne();
        markov.setSeed(25);
        markov.setTraining(st);
        for (int k = 0; k < 3; k++) {
            String text = markov.getRandomText(500);
            printOut(text);
        }
    }

    /**
     * Generates three random texts using MarkovFour.
     * <p>
     * Uses MarkovFour class to generate three random texts, which contains
     * five hundred characters each, and prints the generated texts.
     *
     * @param trainingFilePath full path to the text file
     */
    public void runMarkovFour(String trainingFilePath) {
        SEFileUtil seFileUtil = new SEFileUtil(trainingFilePath);
        String st = seFileUtil.asString();
        st = st.replace('\n', ' ');
        MarkovFour markov = new MarkovFour();
        markov.setSeed(25);
        markov.setTraining(st);
        for (int k = 0; k < 3; k++) {
            String text = markov.getRandomText(500);
            printOut(text);
        }
    }

    /**
     * Generates three random texts using MarkovModel.
     * <p>
     * Uses MarkovModel class with numOfChars=6 to generate three random texts, which contains
     * five hundred characters each, and prints the generated texts.
     *
     * @param trainingFilePath full path to the text file
     */
    public void runMarkovModel(String trainingFilePath) {
        SEFileUtil seFileUtil = new SEFileUtil(trainingFilePath);
        String st = seFileUtil.asString();
        st = st.replace('\n', ' ');
        MarkovModel markov = new MarkovModel(6);
        markov.setSeed(25);
        markov.setTraining(st);
        for (int k = 0; k < 3; k++) {
            String text = markov.getRandomText(500);
            printOut(text);
        }
    }

    /**
     * Prints a given string, in format of sixty characters per line
     *
     * @param s The string to print out
     */
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

    /**
     * Runs the tests of all of the four classes
     * <p>
     * The path to the text file used for the tests is passed as a command line argument.
     *
     * @param args Command line arguments, contains the full path to the text file
     */
    public static void main(String[] args) {
        MarkovRunner markovRunner = new MarkovRunner();
        markovRunner.runMarkovZero(args[0]);
        markovRunner.runMarkovOne(args[0]);
        markovRunner.runMarkovFour(args[0]);
        markovRunner.runMarkovModel(args[0]);
    }
}
