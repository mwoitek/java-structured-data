package solution;

public class CaesarCipher {

  private String alphabet;
  private String shiftedAlphabet;
  private int mainKey;

  public CaesarCipher(int key) {
    this.alphabet = getAlphabet();
    this.shiftedAlphabet = this.getShiftedAlphabet(key);
    this.mainKey = key;
  }

  public static String getAlphabet() {
    StringBuilder alphabet = new StringBuilder();
    for (int i = 65; i < 91; i++) {
      alphabet.append((char) i);
    }
    return alphabet.toString();
  }

  private String getShiftedAlphabet(int key) {
    return this.alphabet.substring(key) + this.alphabet.substring(0, key);
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

  public String decrypt(String input) {
    CaesarCipher cc = new CaesarCipher(this.alphabet.length() - this.mainKey);
    return cc.encrypt(input);
  }

  public static void main(String[] args) {
    CaesarCipher cc = new CaesarCipher(15);
    String input = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
    String encrypted = cc.encrypt(input);

    System.out.println("Problem 1 - Answer:");
    System.out.println(encrypted);
    // Rpc ndj xbpvxct axut LXIWDJI iwt xcitgcti PCS rdbejitgh xc ndjg edrzti?
  }

}
