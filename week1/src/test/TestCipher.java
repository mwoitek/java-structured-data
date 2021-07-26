package test;

import main.Cipher;
import utils.Utils;

public class TestCipher extends Cipher {
  private void testGetShiftedAlphabet() {
    Utils.printStringComparison(getShiftedAlphabet(1), "BCDEFGHIJKLMNOPQRSTUVWXYZA");
    Utils.printStringComparison(getShiftedAlphabet(2), "CDEFGHIJKLMNOPQRSTUVWXYZAB");
    Utils.printStringComparison(getShiftedAlphabet(4), "EFGHIJKLMNOPQRSTUVWXYZABCD");
    Utils.printStringComparison(getShiftedAlphabet(8), "IJKLMNOPQRSTUVWXYZABCDEFGH");
    Utils.printStringComparison(getShiftedAlphabet(16), "QRSTUVWXYZABCDEFGHIJKLMNOP");
  }

  public String encrypt(String input) {
    return input;
  }

  public String decrypt(String input, int idxMostCommonLetter) {
    return input;
  }

  public static void main(String[] args) {
    TestCipher tc = new TestCipher();
    tc.testGetShiftedAlphabet();
  }
}
