package solution;

import java.util.ArrayList;
import java.util.HashMap;
import edu.duke.FileResource;

public class CodonCount {

  private HashMap<String, Integer> codonMap;

  public CodonCount() {
    this.codonMap = new HashMap<String, Integer>();
  }

  private void buildCodonMap(int start, String dna) {
    this.codonMap.clear();

    String codon;
    Integer count;

    int beginIndex = start;
    int endIndex = beginIndex + 3;

    while (endIndex <= dna.length()) {
      codon = dna.substring(beginIndex, endIndex);

      if (this.codonMap.keySet().contains(codon)) {
        count = this.codonMap.get(codon);
        this.codonMap.put(codon, count + 1);
      } else {
        this.codonMap.put(codon, 1);
      }

      beginIndex = endIndex;
      endIndex = beginIndex + 3;
    }
  }

  private String getMostCommonCodon() {
    String mostCommonCodon = "";
    Integer maximumCount = -1;
    for (String codon : this.codonMap.keySet()) {
      if (this.codonMap.get(codon) > maximumCount) {
        mostCommonCodon = codon;
        maximumCount = this.codonMap.get(mostCommonCodon);
      }
    }
    return mostCommonCodon;
  }

  private void printCodonCounts(int start, int end) {
    Integer count;
    System.out.println("Counts of codons between " + start + " and " + end + " inclusive are:");
    for (String codon : this.codonMap.keySet()) {
      count = this.codonMap.get(codon);
      if ((count >= start) && (count <= end)) {
        System.out.println(codon + "\t" + count);
      }
    }
  }

  private void tester() {
    ArrayList<String> lines = new ArrayList<String>();
    FileResource resource = new FileResource();

    for (String line : resource.lines()) {
      lines.add(line.trim().toUpperCase());
    }

    String dna = lines.get(0);
    String mostCommonCodon;

    for (int start = 0; start < 3; start++) {
      this.buildCodonMap(start, dna);
      mostCommonCodon = this.getMostCommonCodon();

      System.out.println("Reading frame starting with " + start + " results in "
          + this.codonMap.size() + " unique codons");
      System.out.println("and most common codon is " + mostCommonCodon + " with count "
          + this.codonMap.get(mostCommonCodon));
      this.printCodonCounts(1, 5);

      System.out.println();
    }
  }

  public static void main(String[] args) {
    CodonCount cc = new CodonCount();
    cc.tester();
  }

}
