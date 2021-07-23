package main;

import utils.Utils;

public class CaesarCipherTwo extends Cipher {
  private String shiftedAlphabet1;
  private String shiftedAlphabet2;

  public CaesarCipherTwo(int key1, int key2) {
    super();
    this.shiftedAlphabet1 = getShiftedAlphabet(key1);
    this.shiftedAlphabet2 = getShiftedAlphabet(key2);
  }

  public String encrypt(String input) {
    char ch;
    char chShifted;
    int idx;
    StringBuilder output = new StringBuilder();

    for (int i = 0; i < input.length(); i++) {
      ch = input.charAt(i);
      idx = this.alphabet.indexOf(Character.toUpperCase(ch));

      if (idx != -1) {
        chShifted =
            Utils.isEven(i) ? this.shiftedAlphabet1.charAt(idx) : this.shiftedAlphabet2.charAt(idx);
        output.append(Character.isLowerCase(ch) ? Character.toLowerCase(chShifted) : chShifted);
      } else {
        output.append(ch);
      }
    }

    return output.toString();
  }
}
