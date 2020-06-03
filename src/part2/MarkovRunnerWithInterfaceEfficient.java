package part2;

import util.SEFileUtil;

public class MarkovRunnerWithInterfaceEfficient {

    private static final int NUMBER_OF_ARGUMENTS = 2;
    private static final int SEED_DEFAULT = 0;

    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
        markov.setSeed(seed);
        System.out.println("running with " + markov);
        for (int k = 0; k < 3; k++) {
            String st = markov.getRandomText(size);
            printOut(st);
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

    public void testHashMap(String trainingFilePath, int seed) {
        SEFileUtil seFileUtil = new SEFileUtil(trainingFilePath);
        String st = seFileUtil.asString();
        st = st.replace('\n', ' ');
        int size = 200;
        EfficientMarkovModel emFive = new EfficientMarkovModel(5);
        runModel(emFive, st, size, seed);
    }

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

        MarkovRunnerWithInterfaceEfficient markovRunner = new MarkovRunnerWithInterfaceEfficient();
        markovRunner.testHashMap(args[0], seed);
        //String path = "C:\\Users\\Dvir\\Desktop\\Software_Engineering\\HW2\\Software_Engineering-HW2\\Data\\merkel.txt";
        //int seed = 42;
        //markovRunner.runMarkov(path, seed);
    }
}
