package solution;

import java.util.HashMap;
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
    char mostCommon = mostCommonCharIn(dictionary);
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

  private static int getMaximumValue(int[] values) {
    int maximumValue = values[0];
    int currentValue;
    for (int i = 1; i < values.length; i++) {
      if ((currentValue = values[i]) > maximumValue) {
        maximumValue = currentValue;
      }
    }
    return maximumValue;
  }

  private static int indexOfMax(int[] values) {
    int idx = 0;
    int maximumValue = getMaximumValue(values);
    while (values[idx] != maximumValue) {
      idx++;
    }
    return idx;
  }

  public char mostCommonCharIn(HashSet<String> dictionary) {
    String alphabet = "abcdefghijklmnopqrstuvwxyz";
    int[] counts = new int[alphabet.length()];
    int idx;
    for (String word : dictionary) {
      for (char c : word.toCharArray()) {
        if ((idx = alphabet.indexOf(c)) != -1) {
          counts[idx]++;
        }
      }
    }
    return alphabet.charAt(indexOfMax(counts));
  }

  public void testMostCommonCharIn() {
    String lang = "English";
    FileResource frDictionary = new FileResource("../txt/dictionaries/" + lang);
    HashSet<String> dictionary = readDictionary(frDictionary);
    System.out.println("language = " + lang);
    System.out.println("most common character = " + mostCommonCharIn(dictionary));

    lang = "Portuguese";
    frDictionary = new FileResource("../txt/dictionaries/" + lang);
    dictionary = readDictionary(frDictionary);
    System.out.println("language = " + lang);
    System.out.println("most common character = " + mostCommonCharIn(dictionary));
  }

  public HashMap<String, HashSet<String>> readDictionaries() {
    HashMap<String, HashSet<String>> dictionaries =
        new HashMap<String, HashSet<String>>();
    String[] langs = {"Danish", "Dutch", "English", "French", "German", "Italian",
        "Portuguese", "Spanish"};
    FileResource fr;
    for (int i = 0; i < langs.length; i++) {
      fr = new FileResource("../txt/dictionaries/" + langs[i]);
      dictionaries.put(langs[i], readDictionary(fr));
    }
    return dictionaries;
  }

  public void breakForAllLangs(String encrypted,
      HashMap<String, HashSet<String>> languages) {
    String langBest = "";
    String decryptedBest = "";
    String decrypted;
    HashSet<String> dictionary;
    int count;
    int maxCount = -1;
    for (String lang : languages.keySet()) {
      dictionary = languages.get(lang);
      decrypted = breakForLanguage(encrypted, dictionary);
      count = countWords(decrypted, dictionary);
      if (count > maxCount) {
        langBest = lang;
        decryptedBest = decrypted;
        maxCount = count;
      }
    }
    System.out.println("Language: " + langBest);
    System.out.println("Decrypted message:");
    System.out.println(decryptedBest);
  }

  public void breakVigenere() {
    FileResource fr = new FileResource();
    String encrypted = fr.asString();
    HashMap<String, HashSet<String>> languages = readDictionaries();
    breakForAllLangs(encrypted, languages);
  }

  public static void main(String[] args) {
    VigenereBreaker vb = new VigenereBreaker();
    vb.breakVigenere();
    // vb.testSliceString();
    // vb.testTryKeyLength();
    // vb.testMostCommonCharIn();
  }

}
