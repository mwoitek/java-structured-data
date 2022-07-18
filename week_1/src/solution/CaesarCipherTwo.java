package solution;

public class CaesarCipherTwo {

  private String alphabet;
  private String shiftedAlphabet1;
  private String shiftedAlphabet2;
  private int mainKey1;
  private int mainKey2;

  public CaesarCipherTwo(int key1, int key2) {
    this.alphabet = CaesarCipher.getAlphabet();
    this.shiftedAlphabet1 = this.getShiftedAlphabet(key1);
    this.shiftedAlphabet2 = this.getShiftedAlphabet(key2);
    this.mainKey1 = key1;
    this.mainKey2 = key2;
  }

  private String getShiftedAlphabet(int key) {
    return this.alphabet.substring(key) + this.alphabet.substring(0, key);
  }

  private static boolean isEven(int num) {
    return num % 2 == 0;
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
            isEven(i) ? this.shiftedAlphabet1.charAt(idx) : this.shiftedAlphabet2.charAt(idx);
        output.append(Character.isLowerCase(ch) ? Character.toLowerCase(chShifted) : chShifted);
      } else {
        output.append(ch);
      }
    }
    return output.toString();
  }

  public String decrypt(String input) {
    int alphabetLength = this.alphabet.length();
    CaesarCipherTwo cc =
        new CaesarCipherTwo(alphabetLength - this.mainKey1, alphabetLength - this.mainKey2);
    return cc.encrypt(input);
  }

  public static void main(String[] args) {
    CaesarCipherTwo cc = new CaesarCipherTwo(21, 8);
    String input = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
    String encrypted = cc.encrypt(input);

    System.out.println("Problem 2 - Answer:");
    System.out.println(encrypted);
    // Xii twp duvodvz gqam EDBCWPB bcm qibzzimo VVY xwhxpbzzn dv gjcm kwxszb?

    cc = new CaesarCipherTwo(14, 24);
    encrypted = "Hfs cpwewloj loks cd Hoto kyg Cyy.";
    String decrypted = cc.decrypt(encrypted);

    System.out.println("Problem 6 - Answer:");
    System.out.println(decrypted);
    // The original name of Java was Oak.
  }

}
