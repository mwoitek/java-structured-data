package solution;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import edu.duke.DirectoryResource;
import edu.duke.FileResource;

public class WordsInFiles {

  private HashMap<String, ArrayList<String>> wordFileMap;

  public WordsInFiles() {
    this.wordFileMap = new HashMap<String, ArrayList<String>>();
  }

  private void addWordsFromFile(File f) {
    String fileName = f.getName();
    ArrayList<String> filesList;
    FileResource resource = new FileResource(f.getAbsolutePath());
    for (String word : resource.words()) {
      if (this.wordFileMap.containsKey(word)) {
        filesList = this.wordFileMap.get(word);
        if (!filesList.contains(fileName)) {
          filesList.add(fileName);
          this.wordFileMap.put(word, filesList);
        }
      } else {
        filesList = new ArrayList<String>();
        filesList.add(fileName);
        this.wordFileMap.put(word, filesList);
      }
    }
  }

  private void buildWordFileMap() {
    this.wordFileMap.clear();
    DirectoryResource dr = new DirectoryResource();
    for (File f : dr.selectedFiles()) {
      this.addWordsFromFile(f);
    }
  }

  private int maxNumber() {
    int maximum = -1;
    int size;
    for (String word : this.wordFileMap.keySet()) {
      size = this.wordFileMap.get(word).size();
      if (size > maximum) {
        maximum = size;
      }
    }
    return maximum;
  }

  private ArrayList<String> wordsInNumFiles(int number) {
    ArrayList<String> words = new ArrayList<String>();
    for (String word : this.wordFileMap.keySet()) {
      if (this.wordFileMap.get(word).size() == number) {
        words.add(word);
      }
    }
    return words;
  }

  private void printFilesIn(String word) {
    for (String fileName : this.wordFileMap.get(word)) {
      System.out.println(fileName);
    }
  }

  public void tester() {
    this.buildWordFileMap();

    int maximum = this.maxNumber();
    System.out.println("Maximum number of files any word is in: " + maximum);
    System.out.println();

    ArrayList<String> words = this.wordsInNumFiles(maximum);
    for (String word : words) {
      System.out.println("Word: " + word);
      System.out.println("File names:");
      this.printFilesIn(word);
      System.out.println();
    }
  }

  public static void main(String[] args) {
    WordsInFiles wif = new WordsInFiles();
    // wif.tester();

    wif.buildWordFileMap();

    ArrayList<String> words = wif.wordsInNumFiles(7);
    System.out.println("Question 12 - Answer: " + words.size());

    words = wif.wordsInNumFiles(4);
    System.out.println("Question 13 - Answer: " + words.size());

    System.out.println("Files in which the word 'laid' DOES appear:");
    wif.printFilesIn("laid");

    System.out.println("Files in which the word 'tree' appears:");
    wif.printFilesIn("tree");
  }

}
