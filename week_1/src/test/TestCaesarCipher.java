package test;

import edu.duke.FileResource;
import solution.CaesarCipher;
import solution.WordLengths;

public class TestCaesarCipher {

  private static int[] countLetters(String input) {
    String alphabet = CaesarCipher.getAlphabet();
    int[] counts = new int[alphabet.length()];
    int idx;
    for (char ch : input.toCharArray()) {
      idx = alphabet.indexOf(Character.toUpperCase(ch));
      if (idx != -1) {
        counts[idx]++;
      }
    }
    return counts;
  }

  public static int getEncryptKey(String input) {
    int[] counts = countLetters(input);
    int idxMaxCounts = WordLengths.indexOfMax(counts);
    int deltaIdx = Math.abs(idxMaxCounts - 4);
    return idxMaxCounts > 4 ? deltaIdx : 26 - deltaIdx;
  }

  public static String breakCaesarCipher(String input) {
    int encryptKey = getEncryptKey(input);
    CaesarCipher cc = new CaesarCipher(26 - encryptKey);
    return cc.encrypt(input);
  }

  private static void simpleTests() {
    FileResource resource = new FileResource();
    String input = "";
    for (String line : resource.lines()) {
      input += line + "\n";
    }
    System.out.println("Original text:");
    System.out.print(input);
    System.out.println();

    CaesarCipher cc = new CaesarCipher(18);
    String encrypted = cc.encrypt(input);
    System.out.println("Encrypted text (key = 18):");
    System.out.print(encrypted);
    System.out.println();

    String decrypted = cc.decrypt(encrypted);
    System.out.println("Decrypted text (using 'decrypt' method):");
    System.out.print(decrypted);
    System.out.println();

    decrypted = breakCaesarCipher(encrypted);
    System.out.println("Decrypted text (using 'breakCaesarCipher' method):");
    System.out.print(decrypted);
  }

  public static void main(String[] args) {
    simpleTests();
  }

}
