package main;

class WordPlay {
  public static boolean isVowel(char ch) {
    return "aeiou".indexOf(Character.toLowerCase(ch)) != -1;
  }

  public static void printBoolean(boolean bool) {
    System.out.println(Boolean.toString(bool));
  }

  public static void testIsVowel() {
    printBoolean(isVowel('a') == true);
    printBoolean(isVowel('F') == false);
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

  public static void testReplaceVowels() {
    printBoolean(replaceVowels("Hello World", '*').equals("H*ll* W*rld"));
    printBoolean(replaceVowels("Another Phrase", '+').equals("+n+th+r Phr+s+"));
  }

  public static boolean isEven(int num) { return num % 2 == 0; }

  public static String emphasize(String phrase, char ch) {
    StringBuilder transformedPhrase = new StringBuilder(phrase);
    char chLower = Character.toLowerCase(ch);
    char chPhraseLower;

    for (int i = 0; i < transformedPhrase.length(); i++) {
      chPhraseLower = Character.toLowerCase(transformedPhrase.charAt(i));
      if (Character.compare(chPhraseLower, chLower) == 0) {
        transformedPhrase.setCharAt(i, isEven(i) ? '+' : '*');
      }
    }

    return transformedPhrase.toString();
  }

  public static void testEmphasize() {
    printBoolean(emphasize("dna ctgaaactga", 'a').equals("dn+ ctg*+*ctg*"));
    printBoolean(
        emphasize("Mary Bella Abracadabra", 'a').equals("M*ry Bell* *br+c+d+br*"));
    printBoolean(emphasize("dna ctgaaactga", 'A').equals("dn+ ctg*+*ctg*"));
    printBoolean(
        emphasize("Mary Bella Abracadabra", 'A').equals("M*ry Bell* *br+c+d+br*"));
  }

  public static void main(String[] args) {
    // Testing the isVowel method
    testIsVowel();

    // Testing the replaceVowels method
    testReplaceVowels();

    // Testing the emphasize method
    testEmphasize();
  }
}
