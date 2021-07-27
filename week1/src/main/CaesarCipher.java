package main;

import utils.Utils;

public class CaesarCipher extends Cipher {
  private String shiftedAlphabet;

  public CaesarCipher(int key) {
    super();
    this.shiftedAlphabet = getShiftedAlphabet(key);
  }

  public String encrypt(String input) {
    char chShifted;
    int idx;
    StringBuilder output = new StringBuilder();

    for (char ch : input.toCharArray()) {
      idx = this.alphabet.indexOf(Character.toUpperCase(ch));

      if (idx != -1) {
        chShifted = this.shiftedAlphabet.charAt(idx);
        output.append(Character.isLowerCase(ch) ? Character.toLowerCase(chShifted) : chShifted);
      } else {
        output.append(ch);
      }
    }

    return output.toString();
  }

  private int[] countLetters(String input) {
    int[] counts = new int[this.alphabet.length()];
    int idx;

    for (char ch : input.toCharArray()) {
      idx = this.alphabet.indexOf(Character.toUpperCase(ch));

      if (idx != -1) {
        counts[idx]++;
      }
    }

    return counts;
  }

  public int getEncryptKey(String input, int idxMostCommonLetter) {
    int[] counts = countLetters(input);
    int idxMaxCounts = Utils.indexOfMax(counts);
    int deltaIdx = Math.abs(idxMaxCounts - idxMostCommonLetter);
    return idxMaxCounts > idxMostCommonLetter ? deltaIdx : this.alphabet.length() - deltaIdx;
  }

  public String decrypt(String input, int idxMostCommonLetter) {
    int encryptKey = getEncryptKey(input, idxMostCommonLetter);
    CaesarCipher cc = new CaesarCipher(this.alphabet.length() - encryptKey);
    return cc.encrypt(input);
  }
}
