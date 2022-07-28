package solution;

import java.util.HashSet;
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

  public HashSet<String> readDictionary(FileResource fr) {
    HashSet<String> dictionary = new HashSet<String>();
    for (String line : fr.lines()) {
      dictionary.add(line.toLowerCase());
    }
    return dictionary;
  }

  public int countWords(String message, HashSet<String> dictionary) {
    int count = 0;
    String[] words = message.split("\\W+");
    for (int i = 0; i < words.length; i++) {
      if (dictionary.contains(words[i].toLowerCase())) {
        count++;
      }
    }
    return count;
  }

  public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
    String decryptedBest = "";
    String decrypted;
    char mostCommon = 'e';
    int[] key;
    VigenereCipher vc;
    int count;
    int maxCount = -1;
    for (int klength = 1; klength <= 100; klength++) {
      key = tryKeyLength(encrypted, klength, mostCommon);
      vc = new VigenereCipher(key);
      decrypted = vc.decrypt(encrypted);
      count = countWords(decrypted, dictionary);
      if (count > maxCount) {
        decryptedBest = decrypted;
        maxCount = count;
      }
    }
    return decryptedBest;
  }

  public void breakVigenere() {
    FileResource frEncrypted = new FileResource("../txt/athens_keyflute.txt");
    String encrypted = frEncrypted.asString();
    FileResource frDictionary = new FileResource("../txt/dictionaries/English");
    HashSet<String> dictionary = readDictionary(frDictionary);
    String decrypted = breakForLanguage(encrypted, dictionary);
    System.out.println(decrypted);
  }

  public static void main(String[] args) {
    VigenereBreaker vb = new VigenereBreaker();
    vb.breakVigenere();

    // vb.testSliceString();
    // vb.testTryKeyLength();
  }

}
