package solution;

import java.util.ArrayList;
import edu.duke.FileResource;

public class WordFrequencies {

  private ArrayList<String> myWords;
  private ArrayList<Integer> myFreqs;

  public WordFrequencies() {
    this.myWords = new ArrayList<String>();
    this.myFreqs = new ArrayList<Integer>();
  }

  public void findUnique() {
    this.myWords.clear();
    this.myFreqs.clear();

    int idx;
    Integer freq;
    FileResource resource = new FileResource();
    String wordLower;

    for (String word : resource.words()) {
      wordLower = word.toLowerCase();
      idx = this.myWords.indexOf(wordLower);

      if (idx == -1) {
        this.myWords.add(wordLower);
        this.myFreqs.add(1);
      } else {
        freq = this.myFreqs.get(idx);
        this.myFreqs.set(idx, freq + 1);
      }
    }
  }

  private static Integer getMaximumValue(ArrayList<Integer> values) {
    Integer maximumValue = values.get(0);
    for (Integer currentValue : values) {
      if (currentValue > maximumValue) {
        maximumValue = currentValue;
      }
    }
    return maximumValue;
  }

  public int findIndexOfMax() {
    Integer maxFreq = getMaximumValue(this.myFreqs);
    return this.myFreqs.indexOf(maxFreq);
  }

  private void tester() {
    this.findUnique();

    int numWords = this.myWords.size();
    System.out.println("Number of unique words: " + numWords);

    Integer freq;
    String word;
    for (int i = 0; i < numWords; i++) {
      freq = this.myFreqs.get(i);
      word = this.myWords.get(i);
      System.out.println(freq + " " + word);
    }

    int idx = this.findIndexOfMax();
    word = this.myWords.get(idx);
    freq = this.myFreqs.get(idx);
    System.out.println("The word that occurs most often and its count are: " + word + " " + freq);
  }

  public static void main(String[] args) {
    WordFrequencies wf = new WordFrequencies();
    wf.tester();
  }

}
