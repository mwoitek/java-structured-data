package solution;

import java.util.ArrayList;
import java.util.Random;
import edu.duke.FileResource;
import edu.duke.URLResource;

public class GladLib {

  private ArrayList<String> adjectiveList;
  private ArrayList<String> animalList;
  private ArrayList<String> colorList;
  private ArrayList<String> countryList;
  private ArrayList<String> fruitList;
  private ArrayList<String> nameList;
  private ArrayList<String> nounList;
  private ArrayList<String> timeList;
  private ArrayList<String> verbList;

  private ArrayList<String> wordsUsed;

  private Random myRandom;

  private static String dataSourceDirectory = "../txt/datalong";
  // private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";

  public GladLib() {
    initializeFromSource(dataSourceDirectory);
    wordsUsed = new ArrayList<String>();
    myRandom = new Random();
  }

  public GladLib(String source) {
    initializeFromSource(source);
    wordsUsed = new ArrayList<String>();
    myRandom = new Random();
  }

  private void initializeFromSource(String source) {
    adjectiveList = readIt(source + "/adjective.txt");
    animalList = readIt(source + "/animal.txt");
    colorList = readIt(source + "/color.txt");
    countryList = readIt(source + "/country.txt");
    fruitList = readIt(source + "/fruit.txt");
    nameList = readIt(source + "/name.txt");
    nounList = readIt(source + "/noun.txt");
    timeList = readIt(source + "/timeframe.txt");
    verbList = readIt(source + "/verb.txt");
  }

  private String randomFrom(ArrayList<String> source) {
    int index = myRandom.nextInt(source.size());
    return source.get(index);
  }

  private String getSubstitute(String label) {
    if (label.equals("adjective")) {
      return randomFrom(adjectiveList);
    }
    if (label.equals("animal")) {
      return randomFrom(animalList);
    }
    if (label.equals("color")) {
      return randomFrom(colorList);
    }
    if (label.equals("country")) {
      return randomFrom(countryList);
    }
    if (label.equals("fruit")) {
      return randomFrom(fruitList);
    }
    if (label.equals("name")) {
      return randomFrom(nameList);
    }
    if (label.equals("noun")) {
      return randomFrom(nounList);
    }
    if (label.equals("number")) {
      return "" + myRandom.nextInt(50) + 5;
    }
    if (label.equals("timeframe")) {
      return randomFrom(timeList);
    }
    if (label.equals("verb")) {
      return randomFrom(verbList);
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

  public void makeStory() {
    wordsUsed.clear();
    String story = fromTemplate("../txt/datalong/madtemplate2.txt");
    printOut(story, 60);
  }

  public static void main(String[] args) {
    GladLib gl = new GladLib();
    gl.makeStory();
  }

}
