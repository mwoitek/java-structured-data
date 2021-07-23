package main;

public class CaesarCipher extends Cipher {
  private String shiftedAlphabet;

  public CaesarCipher(int key) {
    super();
    this.shiftedAlphabet = getShiftedAlphabet(key);
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
        chShifted = this.shiftedAlphabet.charAt(idx);
        output.append(Character.isLowerCase(ch) ? Character.toLowerCase(chShifted) : chShifted);
      } else {
        output.append(ch);
      }
    }

    return output.toString();
  }

  public String decrypt(String input) {
    return input;
  }

  public static void main(String[] args) {
    //
  }
}
