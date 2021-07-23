package main;

import utils.Utils;

public class WordPlay {
  public static boolean isVowel(char ch) {
    return "aeiou".indexOf(Character.toLowerCase(ch)) != -1;
  }

  public static String replaceVowels(String phrase, char ch) {
    StringBuilder transformedPhrase = new StringBuilder(phrase);

    for (int i = 0; i < transformedPhrase.length(); i++) {
      if (isVowel(transformedPhrase.charAt(i))) {
        transformedPhrase.setCharAt(i, ch);
      }
    }

    return transformedPhrase.toString();
  }

  public static String emphasize(String phrase, char ch) {
    StringBuilder transformedPhrase = new StringBuilder(phrase);
    char chLower = Character.toLowerCase(ch);
    char chPhraseLower;

    for (int i = 0; i < transformedPhrase.length(); i++) {
      chPhraseLower = Character.toLowerCase(transformedPhrase.charAt(i));
      if (Character.compare(chPhraseLower, chLower) == 0) {
        transformedPhrase.setCharAt(i, Utils.isEven(i) ? '+' : '*');
      }
    }

    return transformedPhrase.toString();
  }

  public static void main(String[] args) {
    System.out.println("replaceVowels method - Examples:");
    System.out.println();

    System.out.println(
        "replaceVowels(\"Hello World\", '*') = \"" + replaceVowels("Hello World", '*') + "\"");
    System.out.println("replaceVowels(\"Another Phrase\", '+') = \""
        + replaceVowels("Another Phrase", '+') + "\"");
    System.out.println();

    System.out.println("emphasize method - Examples:");
    System.out.println();

    System.out.println(
        "emphasize(\"dna ctgaaactga\", 'a') = \"" + emphasize("dna ctgaaactga", 'a') + "\"");
    System.out.println("emphasize(\"Mary Bella Abracadabra\", 'a') = \""
        + emphasize("Mary Bella Abracadabra", 'a') + "\"");
  }
}
