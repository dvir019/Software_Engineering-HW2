package part2;

import util.SEFileUtil;

/**
 * Execute tests of the Markov classes
 * <p>
 * MarkovZero, MarkovOne, MarkovFour and MarkovModel are being tested,
 * where MarkovModel is tested with numOfChars=3.
 */
public class MarkovRunnerWithInterface {

    private static final int NUMBER_OF_ARGUMENTS = 2;
    private static final int SEED_DEFAULT = 0;

    /**
     * Generates three random texts using the given markov.
     * <p>
     * Uses the given markov to generate three random texts, which contains
     * a given number of characters each, and prints the generated texts.
     *
     * @param markov The markov object
     * @param text   The training text of the markov object
     * @param size   The numbers of characters in the generated text
     * @param seed   The seed of the random object
     */
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
        markov.setSeed(seed);
        System.out.println("running with " + markov);
        for (int k = 0; k < 3; k++) {
            String st = markov.getRandomText(size);
            printOut(st);
        }
    }

    /**
     * Runs tests on all of the Markov classes
     *
     * @param trainingFilePath full path to the text file
     * @param seed             The seed of the random
     */
    public void runMarkov(String trainingFilePath, int seed) {
        SEFileUtil seFileUtil = new SEFileUtil(trainingFilePath);
        String st = seFileUtil.asString();
        st = st.replace('\n', ' ');
        int size = 200;

        // Test MarkovZero
        MarkovZero mz = new MarkovZero();
        runModel(mz, st, size, seed);

        // Test MarkovOne
        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size, seed);

        // Test MarkovModel, with numOfChars=3
        MarkovModel mThree = new MarkovModel(3);
        runModel(mThree, st, size, seed);

        // Test MarkovFour
        MarkovFour mFour = new MarkovFour();
        runModel(mFour, st, size, seed);

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
     * Runs the tests for the markov classes.
     * <p>
     * At first, the command line arguments are checked, as the args array
     * should contain exactly two arguments:
     *      - The full path to the text file
     *      - An integer representing the seed for the random object.
     * <p>
     * If the arguments are invalid, a message is printed, and the program ends.
     * <p>
     * If the arguments are valid, the tests will be performed
     *
     * @param args Array of command line arguments
     */
    public static void main(String[] args) {
        if (args.length != NUMBER_OF_ARGUMENTS) {
            System.out.println("Please pass two arguments: 1.input_file 2.seed");
            System.exit(1);
        }
        int seed = SEED_DEFAULT;
        try {
            seed = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("The second argument must be an integer");
            System.exit(1);
        }

        String path = args[0];

        MarkovRunnerWithInterface markovRunner = new MarkovRunnerWithInterface();
        markovRunner.runMarkov(path, seed);
    }
}
