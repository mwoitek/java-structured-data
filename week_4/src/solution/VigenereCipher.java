package solution;

import java.util.Arrays;
import edu.duke.FileResource;

public class VigenereCipher {

  CaesarCipher[] ciphers;

  public VigenereCipher(int[] key) {
    ciphers = new CaesarCipher[key.length];
    for (int i = 0; i < key.length; i++) {
      ciphers[i] = new CaesarCipher(key[i]);
    }
  }

  public String encrypt(String input) {
    StringBuilder answer = new StringBuilder();
    int i = 0;
    for (char c : input.toCharArray()) {
      int cipherIndex = i % ciphers.length;
      CaesarCipher thisCipher = ciphers[cipherIndex];
      answer.append(thisCipher.encryptLetter(c));
      i++;
    }
    return answer.toString();
  }

  public String decrypt(String input) {
    StringBuilder answer = new StringBuilder();
    int i = 0;
    for (char c : input.toCharArray()) {
      int cipherIndex = i % ciphers.length;
      CaesarCipher thisCipher = ciphers[cipherIndex];
      answer.append(thisCipher.decryptLetter(c));
      i++;
    }
    return answer.toString();
  }

  public String toString() {
    return Arrays.toString(ciphers);
  }

  public static void main(String[] args) {
    int[] key = {17, 14, 12, 4};
    VigenereCipher vc = new VigenereCipher(key);
    FileResource fr = new FileResource("../txt/titus-small.txt");
    String input = fr.asString();
    System.out.println(input);
    String encrypted = vc.encrypt(input);
    System.out.println(encrypted);
    String decrypted = vc.decrypt(encrypted);
    System.out.println(decrypted);
  }

}
