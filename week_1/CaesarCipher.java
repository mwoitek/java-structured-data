import java.io.*;

class CaesarCipher {
  public static String readTextFile(String fileName) {
    try {
      File textFile = new File(fileName);
      FileReader reader = new FileReader(textFile);
      BufferedReader bufReader = new BufferedReader(reader);

      StringBuffer strBuffer = new StringBuffer();
      String line;

      while ((line = bufReader.readLine()) != null) {
        strBuffer.append(line + "\n");
      }
      reader.close();

      return strBuffer.toString().trim();
    } catch (IOException e) {
      System.out.println("Cannot read the text file " + fileName + "!");
      return "";
    }
  }

  public static void testReadTextFile() {
    System.out.println(readTextFile("test_file.txt"));
    System.out.println(readTextFile("non_existent_file.txt"));
  }

  public static String getAlphabet() {
    StringBuilder alphabet = new StringBuilder();
    for (int i = 65; i < 91; i++) {
      alphabet.append((char)i);
    }
    return alphabet.toString();
  }

  public static String getShiftedAlphabet(String alphabet, int key) {
    return alphabet.substring(key) + alphabet.substring(0, key);
  }

  public static void printBoolean(boolean bool) {
    System.out.println(Boolean.toString(bool));
  }

  public static void testGetShiftedAlphabet() {
    String alphabet = getAlphabet();
    printBoolean(getShiftedAlphabet(alphabet, 1).equals("BCDEFGHIJKLMNOPQRSTUVWXYZA"));
    printBoolean(getShiftedAlphabet(alphabet, 2).equals("CDEFGHIJKLMNOPQRSTUVWXYZAB"));
    printBoolean(getShiftedAlphabet(alphabet, 4).equals("EFGHIJKLMNOPQRSTUVWXYZABCD"));
    printBoolean(getShiftedAlphabet(alphabet, 8).equals("IJKLMNOPQRSTUVWXYZABCDEFGH"));
    printBoolean(getShiftedAlphabet(alphabet, 16).equals("QRSTUVWXYZABCDEFGHIJKLMNOP"));
  }

  public static String encrypt(String input, int key) {
    char ch;
    char chShifted;
    int idx;

    String alphabet = getAlphabet();
    String shiftedAlphabet = getShiftedAlphabet(alphabet, key);
    StringBuilder encryptedInput = new StringBuilder();

    for (int i = 0; i < input.length(); i++) {
      ch = input.charAt(i);
      idx = alphabet.indexOf(Character.toUpperCase(ch));

      if (idx != -1) {
        chShifted = shiftedAlphabet.charAt(idx);
        encryptedInput.append(Character.isLowerCase(ch) ? Character.toLowerCase(chShifted)
                                                        : chShifted);
      } else {
        encryptedInput.append(ch);
      }
    }

    return encryptedInput.toString();
  }

  public static void encryptTextFile(String fileName, int key) {
    String message = readTextFile(fileName);
    String encryptedMessage = encrypt(message, key);

    System.out.println("Original message:");
    System.out.println(message);

    System.out.println();

    System.out.println("Encrypted message (key = " + String.valueOf(key) + "):");
    System.out.println(encryptedMessage);
  }

  public static void testCaesar() {
    String fileName = "test_caesar.txt";
    encryptTextFile(fileName, 17);
    System.out.println();
    encryptTextFile(fileName, 23);
  }

  public static void main(String[] args) {
    // Testing the readTextFile method
    // testReadTextFile();

    // Testing the getAlphabet method
    // System.out.println(getAlphabet());

    // Testing the getShiftedAlphabet method
    // testGetShiftedAlphabet();

    // Testing my implementation of the Caesar cipher
    testCaesar();
  }
}
