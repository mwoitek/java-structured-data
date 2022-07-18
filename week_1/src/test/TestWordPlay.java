package test;

import solution.WordPlay;

public class TestWordPlay {

  private static void printBoolean(boolean bool) {
    System.out.println(Boolean.toString(bool));
  }

  private static void testIsVowel() {
    printBoolean(WordPlay.isVowel('a') == true);
    printBoolean(WordPlay.isVowel('A') == true);
    printBoolean(WordPlay.isVowel('f') == false);
    printBoolean(WordPlay.isVowel('F') == false);
  }

  private static boolean areStringsEqual(String string1, String string2) {
    return string1.equals(string2);
  }

  private static void printStringComparison(String string1, String string2) {
    printBoolean(areStringsEqual(string1, string2));
  }

  private static void testReplaceVowels() {
    printStringComparison(WordPlay.replaceVowels("Hello World", '*'), "H*ll* W*rld");
    printStringComparison(WordPlay.replaceVowels("Another Phrase", '+'), "+n+th+r Phr+s+");
  }

  private static void testEmphasize() {
    printStringComparison(WordPlay.emphasize("dna ctgaaactga", 'a'), "dn+ ctg*+*ctg*");
    printStringComparison(WordPlay.emphasize("Mary Bella Abracadabra", 'a'),
        "M*ry Bell* *br+c+d+br*");
    printStringComparison(WordPlay.emphasize("dna ctgaaactga", 'A'), "dn+ ctg*+*ctg*");
    printStringComparison(WordPlay.emphasize("Mary Bella Abracadabra", 'A'),
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
