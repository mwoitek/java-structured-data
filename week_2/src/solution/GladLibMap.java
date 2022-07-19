package solution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import edu.duke.FileResource;
import edu.duke.URLResource;

public class GladLibMap {

  private HashMap<String, ArrayList<String>> myMap;
  private ArrayList<String> categoriesUsed;
  private ArrayList<String> wordsUsed;
  private Random myRandom;

  private static String dataSourceDirectory = "../txt/datalong";
  // private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";

  public GladLibMap() {
    myMap = new HashMap<String, ArrayList<String>>();
    initializeFromSource(dataSourceDirectory);
    categoriesUsed = new ArrayList<String>();
    wordsUsed = new ArrayList<String>();
    myRandom = new Random();
  }

  public GladLibMap(String source) {
    myMap = new HashMap<String, ArrayList<String>>();
    initializeFromSource(source);
    categoriesUsed = new ArrayList<String>();
    wordsUsed = new ArrayList<String>();
    myRandom = new Random();
  }

  private void initializeFromSource(String source) {
    String[] categories =
        {"adjective", "animal", "color", "country", "fruit", "name", "noun", "timeframe", "verb"};
    for (String category : categories) {
      myMap.put(category, readIt(source + "/" + category + ".txt"));
    }
  }

  private String randomFrom(ArrayList<String> source) {
    int index = myRandom.nextInt(source.size());
    return source.get(index);
  }

  private String getSubstitute(String label) {
    if (myMap.containsKey(label)) {
      if (!categoriesUsed.contains(label)) {
        categoriesUsed.add(label);
      }
      return randomFrom(myMap.get(label));
    }
    if (label.equals("number")) {
      return "" + myRandom.nextInt(50) + 5;
    }
    return "**UNKNOWN**";
  }

  private String processWord(String w) {
    int first = w.indexOf("<");
    int last = w.indexOf(">", first);
    if (first == -1 || last == -1) {
      return w;
    }
    String prefix = w.substring(0, first);
    String suffix = w.substring(last + 1);
    String sub = getSubstitute(w.substring(first + 1, last));
    while (wordsUsed.contains(sub)) {
      sub = getSubstitute(w.substring(first + 1, last));
    }
    wordsUsed.add(sub);
    return prefix + sub + suffix;
  }

  private void printOut(String s, int lineWidth) {
    int charsWritten = 0;
    for (String w : s.split("\\s+")) {
      if (charsWritten + w.length() > lineWidth) {
        System.out.println();
        charsWritten = 0;
      }
      System.out.print(w + " ");
      charsWritten += w.length() + 1;
    }
  }

  private String fromTemplate(String source) {
    String story = "";
    if (source.startsWith("http")) {
      URLResource resource = new URLResource(source);
      for (String word : resource.words()) {
        story = story + processWord(word) + " ";
      }
    } else {
      FileResource resource = new FileResource(source);
      for (String word : resource.words()) {
        story = story + processWord(word) + " ";
      }
    }
    return story;
  }

  private ArrayList<String> readIt(String source) {
    ArrayList<String> list = new ArrayList<String>();
    if (source.startsWith("http")) {
      URLResource resource = new URLResource(source);
      for (String line : resource.lines()) {
        list.add(line);
      }
    } else {
      FileResource resource = new FileResource(source);
      for (String line : resource.lines()) {
        list.add(line);
      }
    }
    return list;
  }

  public int totalWordsInMap() {
    int total = 0;
    for (String category : myMap.keySet()) {
      total += myMap.get(category).size();
    }
    return total;
  }

  public int totalWordsConsidered() {
    int total = 0;
    for (String category : categoriesUsed) {
      total += myMap.get(category).size();
    }
    return total;
  }

  public void makeStory() {
    categoriesUsed.clear();
    wordsUsed.clear();

    String story = fromTemplate("../txt/datalong/madtemplate2.txt");
    printOut(story, 60);

    System.out.println();
    System.out.println();

    System.out
        .println("Total number of words that were possible to pick from: " + totalWordsInMap());
    System.out.println();
    System.out.println(
        "Total number of words in the categories that were used: " + totalWordsConsidered());
  }

  public static void main(String[] args) {
    GladLibMap glm = new GladLibMap();
    glm.makeStory();
  }

}
