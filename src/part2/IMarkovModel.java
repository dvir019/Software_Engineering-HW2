package part2;

public interface IMarkovModel {
    /**
     * Sets myText to the given string
     *
     * @param text The new training text
     */
    public void setTraining(String text);

    /**
     * Chooses randomly numChars characters from the training text
     *
     * @param numChars The total number of characters to choose from the text
     * @return The chosen characters, as a string
     */
    public String getRandomText(int numChars);

    public void setSeed(int seed);
}
