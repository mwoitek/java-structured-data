package solution;

import edu.duke.FileResource;

public class CaesarCracker {

  char mostCommon;

  public CaesarCracker() {
    mostCommon = 'e';
  }

  public CaesarCracker(char c) {
    mostCommon = c;
  }

  public int[] countLetters(String message) {
    String alph = "abcdefghijklmnopqrstuvwxyz";
    int[] counts = new int[26];
    for (int k = 0; k < message.length(); k++) {
      int dex = alph.indexOf(Character.toLowerCase(message.charAt(k)));
      if (dex != -1) {
        counts[dex] += 1;
      }
    }
    return counts;
  }

  public int maxIndex(int[] vals) {
    int maxDex = 0;
    for (int k = 0; k < vals.length; k++) {
      if (vals[k] > vals[maxDex]) {
        maxDex = k;
      }
    }
    return maxDex;
  }

  public int getKey(String encrypted) {
    int[] freqs = countLetters(encrypted);
    int maxDex = maxIndex(freqs);
    int mostCommonPos = mostCommon - 'a';
    int dkey = maxDex - mostCommonPos;
    if (maxDex < mostCommonPos) {
      dkey = 26 - (mostCommonPos - maxDex);
    }
    return dkey;
  }

  public String decrypt(String encrypted) {
    int key = getKey(encrypted);
    CaesarCipher cc = new CaesarCipher(key);
    return cc.decrypt(encrypted);
  }

  public static void main(String[] args) {
    CaesarCracker cc = new CaesarCracker();
    FileResource fr = new FileResource("../txt/titus-small_key5.txt");
    String encrypted = fr.asString();
    System.out.println(encrypted);
    int key = cc.getKey(encrypted);
    System.out.println("key = " + key + "\n");
    String decrypted = cc.decrypt(encrypted);
    System.out.println(decrypted);

    cc = new CaesarCracker('a');
    fr = new FileResource("../txt/oslusiadas_key17.txt");
    encrypted = fr.asString();
    System.out.println(encrypted);
    key = cc.getKey(encrypted);
    System.out.println("key = " + key + "\n");
    decrypted = cc.decrypt(encrypted);
    System.out.println(decrypted);
  }

}
