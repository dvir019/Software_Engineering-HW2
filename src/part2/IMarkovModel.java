package part2;

public interface IMarkovModel {
    public void setTraining(String text);
    
    public String getRandomText(int numChars);

    public void setSeed(int seed);
}
