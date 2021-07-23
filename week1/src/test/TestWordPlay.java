package test;

import main.WordPlay;
import utils.Utils;

public class TestWordPlay {
  private static void testIsVowel() {
    Utils.printBoolean(WordPlay.isVowel('a') == true);
    Utils.printBoolean(WordPlay.isVowel('A') == true);
    Utils.printBoolean(WordPlay.isVowel('f') == false);
    Utils.printBoolean(WordPlay.isVowel('F') == false);
  }

  private static void testReplaceVowels() {
    Utils.printStringComparison(WordPlay.replaceVowels("Hello World", '*'), "H*ll* W*rld");
    Utils.printStringComparison(WordPlay.replaceVowels("Another Phrase", '+'), "+n+th+r Phr+s+");
  }

  private static void testEmphasize() {
    Utils.printStringComparison(WordPlay.emphasize("dna ctgaaactga", 'a'), "dn+ ctg*+*ctg*");
    Utils.printStringComparison(WordPlay.emphasize("Mary Bella Abracadabra", 'a'),
        "M*ry Bell* *br+c+d+br*");
    Utils.printStringComparison(WordPlay.emphasize("dna ctgaaactga", 'A'), "dn+ ctg*+*ctg*");
    Utils.printStringComparison(WordPlay.emphasize("Mary Bella Abracadabra", 'A'),
        "M*ry Bell* *br+c+d+br*");
  }

  public static void main(String[] args) {
    System.out.println("Testing the isVowel method");
    testIsVowel();
    System.out.println();

    System.out.println("Testing the replaceVowels method");
    testReplaceVowels();
    System.out.println();

    System.out.println("Testing the emphasize method");
    testEmphasize();
  }
}
