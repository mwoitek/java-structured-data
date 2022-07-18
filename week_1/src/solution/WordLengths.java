package solution;

import edu.duke.FileResource;

public class WordLengths {

  private static boolean isFirstCharNonLetter(String word) {
    return !Character.isLetter(word.charAt(0));
  }

  private static boolean isLastCharNonLetter(String word) {
    return !Character.isLetter(word.charAt(word.length() - 1));
  }

  private static int computeRealLength(String word) {
    int realLength = word.length();
    if (isFirstCharNonLetter(word)) {
      realLength--;
    }
    if (isLastCharNonLetter(word)) {
      realLength--;
    }
    return realLength;
  }

  private static int getCountsIndex(String word) {
    int realLength = computeRealLength(word);
    return realLength < 30 ? realLength - 1 : 29;
  }

  public static void countWordLengths(FileResource resource, int[] counts) {
    for (String word : resource.words()) {
      try {
        counts[getCountsIndex(word)]++;
      } catch (Exception e) {
        continue;
      }
    }
  }

  private static void printCounts(int[] counts) {
    int count;
    int length;
    for (int i = 0; i < 29; i++) {
      count = counts[i];
      length = i + 1;
      if (count == 1) {
        System.out.println("1 word of length " + length);
      } else if (count > 1) {
        System.out.println(count + " words of length " + length);
      }
    }
    count = counts[29];
    if (count == 1) {
      System.out.println("1 word of length 30 or greater");
    } else if (count > 1) {
      System.out.println(count + " words of length 30 or greater");
    }
  }

  private static int getMaximumValue(int[] values) {
    // Only works if array is non-empty
    int currentValue;
    int maximumValue = values[0];
    for (int i = 1; i < values.length; i++) {
      if ((currentValue = values[i]) > maximumValue) {
        maximumValue = currentValue;
      }
    }
    return maximumValue;
  }

  public static int indexOfMax(int[] values) {
    int idx = 0;
    int maximumValue = getMaximumValue(values);
    while (values[idx] != maximumValue) {
      idx++;
    }
    return idx;
  }

  private static void printMostCommonWordLength(int[] counts) {
    int mostCommonWordLength = indexOfMax(counts) + 1;
    System.out.println("Most common word length: " + mostCommonWordLength);
  }

  private static void testCountWordLengths() {
    FileResource resource = new FileResource();
    int[] counts = new int[30];
    countWordLengths(resource, counts);
    printCounts(counts);
    printMostCommonWordLength(counts);
  }

  public static void main(String[] args) {
    testCountWordLengths();
  }

}
