package utils;

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
}
