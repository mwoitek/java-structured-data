package solution;

import edu.duke.FileResource;

public class CaesarCipher {

  private String alphabet;
  private String shiftedAlphabet;
  private int theKey;

  public CaesarCipher(int key) {
    theKey = key;
    alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
    alphabet = alphabet + alphabet.toLowerCase();
    shiftedAlphabet = shiftedAlphabet + shiftedAlphabet.toLowerCase();
  }

  private char transformLetter(char c, String from, String to) {
    int idx = from.indexOf(c);
    if (idx != -1) {
      return to.charAt(idx);
    }
    return c;
  }

  public char encryptLetter(char c) {
    return transformLetter(c, alphabet, shiftedAlphabet);
  }

  public char decryptLetter(char c) {
    return transformLetter(c, shiftedAlphabet, alphabet);
  }

  private String transform(String input, String from, String to) {
    StringBuilder sb = new StringBuilder(input);
    for (int i = 0; i < sb.length(); i++) {
      char c = sb.charAt(i);
      c = transformLetter(c, from, to);
      sb.setCharAt(i, c);
    }
    return sb.toString();
  }

  public String encrypt(String input) {
    return transform(input, alphabet, shiftedAlphabet);
  }

  public String decrypt(String input) {
    return transform(input, shiftedAlphabet, alphabet);
  }

  public String toString() {
    return "" + theKey;
  }

  public static void main(String[] args) {
    CaesarCipher cc = new CaesarCipher(17);
    FileResource fr = new FileResource("../txt/titus-small.txt");
    String input = fr.asString();
    System.out.println(input);
    String encrypted = cc.encrypt(input);
    System.out.println(encrypted);
    String decrypted = cc.decrypt(encrypted);
    System.out.println(decrypted);
  }

}
