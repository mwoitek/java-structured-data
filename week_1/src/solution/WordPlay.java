package solution;

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

  private static boolean isEven(int num) {
    return num % 2 == 0;
  }

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

}
