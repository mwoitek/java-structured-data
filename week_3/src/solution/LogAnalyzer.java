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

  public int countUniqueIPs() {
    String ipAddress;
    ArrayList<String> uniqueIPs = new ArrayList<String>();
    for (LogEntry le : records) {
      ipAddress = le.getIpAddress();
      if (!uniqueIPs.contains(ipAddress)) {
        uniqueIPs.add(ipAddress);
      }
    }
    return uniqueIPs.size();
  }

  public void printAllHigherThanNum(int num) {
    for (LogEntry le : records) {
      if (le.getStatusCode() > num) {
        System.out.println(le);
      }
    }
  }

  public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
    String day;
    String ipAddress;
    ArrayList<String> uniqueIPs = new ArrayList<String>();
    for (LogEntry le : records) {
      day = le.getAccessTime().toString().substring(4, 10);
      ipAddress = le.getIpAddress();
      if (day.equals(someday) && !uniqueIPs.contains(ipAddress)) {
        uniqueIPs.add(ipAddress);
      }
    }
    return uniqueIPs;
  }

  public int countUniqueIPsInRange(int low, int high) {
    String ipAddress;
    int statusCode;
    ArrayList<String> uniqueIPs = new ArrayList<String>();
    for (LogEntry le : records) {
      ipAddress = le.getIpAddress();
      statusCode = le.getStatusCode();
      if (statusCode >= low && statusCode <= high && !uniqueIPs.contains(ipAddress)) {
        uniqueIPs.add(ipAddress);
      }
    }
    return uniqueIPs.size();
  }

}
