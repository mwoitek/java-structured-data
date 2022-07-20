package solution;

import java.util.ArrayList;

public class LogAnalyzer {

  private ArrayList<LogEntry> records;

  public LogAnalyzer() {
    // complete constructor
  }

  public void readFile(String filename) {
    // complete method
  }

  public void printAll() {
    for (LogEntry le : records) {
      System.out.println(le);
    }
  }

}
