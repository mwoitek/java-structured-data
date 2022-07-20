package solution;

import java.util.ArrayList;
import edu.duke.FileResource;

public class LogAnalyzer {

  private ArrayList<LogEntry> records;

  public LogAnalyzer() {
    records = new ArrayList<LogEntry>();
  }

  public void readFile(String filename) {
    FileResource fr = new FileResource(filename);
    LogEntry le;
    for (String line : fr.lines()) {
      le = WebLogParser.parseEntry(line);
      records.add(le);
    }
  }

  public void printAll() {
    for (LogEntry le : records) {
      System.out.println(le);
    }
  }

}
