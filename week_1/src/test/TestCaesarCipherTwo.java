package test;

import edu.duke.FileResource;
import solution.CaesarCipherTwo;

public class TestCaesarCipherTwo {

  private static String halfOfString(String input, int start) {
    StringBuilder output = new StringBuilder();
    for (int i = start; i < input.length(); i += 2) {
      output.append(input.charAt(i));
    }
    return output.toString();
  }

  public static String breakCaesarCipher(String input) {
    String half1 = halfOfString(input, 0);
    String half2 = halfOfString(input, 1);

    int encryptKey1 = TestCaesarCipher.getEncryptKey(half1);
    int encryptKey2 = TestCaesarCipher.getEncryptKey(half2);

    CaesarCipherTwo cc = new CaesarCipherTwo(26 - encryptKey1, 26 - encryptKey2);
    return cc.encrypt(input);
  }

  private static void simpleTests() {
    FileResource resource = new FileResource("../txt/test_caesar.txt");
    String input = "";
    for (String line : resource.lines()) {
      input += line + "\n";
    }
    System.out.println("Original text:");
    System.out.print(input);
    System.out.println();

    CaesarCipherTwo cc = new CaesarCipherTwo(17, 3);
    String encrypted = cc.encrypt(input);
    System.out.println("Encrypted text (key1 = 17, key2 = 3):");
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
    System.out.println();

    String encrypted = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";
    String decrypted = breakCaesarCipher(encrypted);

    System.out.println("Problem 7 - Answer:");
    System.out.println(decrypted);
    // The name of the Java Mascot is Duke. Woeoeee!

    System.out.println();

    FileResource resource = new FileResource("../txt/mysteryTwoKeysQuiz.txt");
    encrypted = "";
    for (String line : resource.lines()) {
      encrypted += line + "\n";
    }
    decrypted = breakCaesarCipher(encrypted);

    System.out.println("Problem 8 - Answer:");
    System.out.print(decrypted);
    // Duke Computer Science Department Overview

    System.out.println();

    String half1 = halfOfString(encrypted, 0);
    String half2 = halfOfString(encrypted, 1);
    int encryptKey1 = TestCaesarCipher.getEncryptKey(half1);
    int encryptKey2 = TestCaesarCipher.getEncryptKey(half2);

    System.out.println("Problem 9 - Answer:");
    System.out.println(encryptKey1 + "," + encryptKey2);
    // 17,4
  }

}
