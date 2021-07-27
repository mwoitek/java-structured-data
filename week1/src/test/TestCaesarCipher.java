package test;

import main.CaesarCipher;
import utils.Utils;

public class TestCaesarCipher {
  private static void simpleTest(String fileName, int key) {
    String strTextFile = Utils.readTextFile(fileName);
    System.out.println("Original text:");
    System.out.println(strTextFile);
    System.out.println();

    CaesarCipher cc = new CaesarCipher(key);
    String encrypted = cc.encrypt(strTextFile);
    System.out.println("Encrypted text (key = " + key + "):");
    System.out.println(encrypted);
    System.out.println();

    String decrypted = cc.decrypt(encrypted, 4);
    System.out.println("Decrypted text:");
    System.out.println(decrypted);
  }

  public static void main(String[] args) {
    String fileName = args[0];
    int key = Integer.parseInt(args[1]);
    simpleTest(fileName, key);
  }
}
