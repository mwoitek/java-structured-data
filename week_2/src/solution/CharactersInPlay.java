package solution;

import java.util.ArrayList;
import edu.duke.FileResource;

public class CharactersInPlay {

  private ArrayList<Integer> counts;
  private ArrayList<String> names;

  public CharactersInPlay() {
    this.counts = new ArrayList<Integer>();
    this.names = new ArrayList<String>();
  }

  private String extractName(String line) {
    int idx = line.indexOf(".");
    return line.substring(0, idx).stripLeading();
  }

  private void update(String person) {
    int idx = this.names.indexOf(person);
    if (idx == -1) {
      this.names.add(person);
      this.counts.add(1);
    } else {
      Integer count = this.counts.get(idx);
      this.counts.set(idx, count + 1);
    }
  }

  public void findAllCharacters() {
    this.counts.clear();
    this.names.clear();

    String name;
    FileResource resource = new FileResource();
    for (String line : resource.lines()) {
      if (line.contains(".")) {
        name = this.extractName(line);
        this.update(name);
      }
    }
  }

  private void tester() {
    this.findAllCharacters();

    Integer count;
    String name;
    for (int i = 0; i < this.names.size(); i++) {
      count = this.counts.get(i);
      if (count > 1) {
        name = this.names.get(i);
        System.out.println(String.format("%-25s", name) + "\t" + count);
      }
    }
  }

  public static void main(String[] args) {
    CharactersInPlay cp = new CharactersInPlay();
    cp.tester();
  }

}
