package solution;

import edu.duke.FileResource;

public class VigenereBreaker {

  public String sliceString(String message, int whichSlice, int totalSlices) {
    StringBuilder sb = new StringBuilder();
    for (int i = whichSlice; i < message.length(); i += totalSlices) {
      sb.append(message.charAt(i));
    }
    return sb.toString();
  }

  public void testSliceString() {
    System.out.println(sliceString("abcdefghijklm", 0, 3)); // adgjm
    System.out.println(sliceString("abcdefghijklm", 1, 3)); // behk
    System.out.println(sliceString("abcdefghijklm", 2, 3)); // cfil
    System.out.println(sliceString("abcdefghijklm", 0, 4)); // aeim
    System.out.println(sliceString("abcdefghijklm", 1, 4)); // bfj
    System.out.println(sliceString("abcdefghijklm", 2, 4)); // cgk
    System.out.println(sliceString("abcdefghijklm", 3, 4)); // dhl
    System.out.println(sliceString("abcdefghijklm", 0, 5)); // afk
    System.out.println(sliceString("abcdefghijklm", 1, 5)); // bgl
    System.out.println(sliceString("abcdefghijklm", 2, 5)); // chm
    System.out.println(sliceString("abcdefghijklm", 3, 5)); // di
    System.out.println(sliceString("abcdefghijklm", 4, 5)); // ej
  }

  public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
    int[] key = new int[klength];
    CaesarCracker cc = new CaesarCracker(mostCommon);
    String slice;
    for (int i = 0; i < klength; i++) {
      slice = sliceString(encrypted, i, klength);
      key[i] = cc.getKey(slice);
    }
    return key;
  }

  public void testTryKeyLength() {
    FileResource fr = new FileResource("../txt/athens_keyflute.txt");
    String encrypted = fr.asString();
    int[] key = tryKeyLength(encrypted, 5, 'e');
    System.out.println("Key:");
    for (int i = 0; i < 5; i++) {
      System.out.println(key[i]);
    }
  }

  public void breakVigenere() {
    FileResource fr = new FileResource("../txt/athens_keyflute.txt");
    String encrypted = fr.asString();
    int klength = 5;
    char mostCommon = 'e';
    int[] key = tryKeyLength(encrypted, klength, mostCommon);
    VigenereCipher vc = new VigenereCipher(key);
    String decrypted = vc.decrypt(encrypted);
    System.out.println(decrypted);
  }

  public static void main(String[] args) {
    VigenereBreaker vb = new VigenereBreaker();

    // vb.testSliceString();
    // vb.testTryKeyLength();

    vb.breakVigenere();
  }

}
