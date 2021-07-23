package utils;

import java.io.*;

public class Utils {
  public static boolean isEven(int num) {
    return num % 2 == 0;
  }

  public static boolean areStringsEqual(String string1, String string2) {
    return string1.equals(string2);
  }

  public static void printBoolean(boolean bool) {
    System.out.println(Boolean.toString(bool));
  }

  public static void printStringComparison(String string1, String string2) {
    printBoolean(areStringsEqual(string1, string2));
  }

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
}
