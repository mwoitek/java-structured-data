package solution;

import java.util.ArrayList;
import java.util.HashMap;
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

  public HashMap<String, Integer> countVisitsPerIP() {
    HashMap<String, Integer> counts = new HashMap<String, Integer>();
    String ipAddress;
    for (LogEntry le : records) {
      ipAddress = le.getIpAddress();
      if (counts.containsKey(ipAddress)) {
        counts.put(ipAddress, counts.get(ipAddress) + 1);
      } else {
        counts.put(ipAddress, 1);
      }
    }
    return counts;
  }

  public Integer mostNumberVisitsByIP(HashMap<String, Integer> counts) {
    Integer max = 0;
    for (String ipAddress : counts.keySet()) {
      if (counts.get(ipAddress) > max) {
        max = counts.get(ipAddress);
      }
    }
    return max;
  }

  public ArrayList<String> iPsMostVisits(HashMap<String, Integer> counts) {
    ArrayList<String> ips = new ArrayList<String>();
    Integer max = mostNumberVisitsByIP(counts);
    for (String ip : counts.keySet()) {
      if (counts.get(ip) == max) {
        ips.add(ip);
      }
    }
    return ips;
  }

  public HashMap<String, ArrayList<String>> iPsForDays() {
    String day;
    HashMap<String, ArrayList<String>> daysIPsMap = new HashMap<String, ArrayList<String>>();
    ArrayList<String> ipsList;
    for (LogEntry le : records) {
      day = le.getAccessTime().toString().substring(4, 10);
      if (daysIPsMap.containsKey(day)) {
        ipsList = daysIPsMap.get(day);
      } else {
        ipsList = new ArrayList<String>();
      }
      ipsList.add(le.getIpAddress());
      daysIPsMap.put(day, ipsList);
    }
    return daysIPsMap;
  }

  public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> daysIPsMap) {
    String dayMax = "";
    int maxNumIPs = 0;
    int numIPs;
    for (String day : daysIPsMap.keySet()) {
      numIPs = daysIPsMap.get(day).size();
      if (numIPs > maxNumIPs) {
        dayMax = day;
        maxNumIPs = numIPs;
      }
    }
    return dayMax;
  }

  public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> daysIPsMap,
      String day) {
    HashMap<String, Integer> counts = new HashMap<String, Integer>();
    ArrayList<String> ips = daysIPsMap.get(day);
    for (String ip : ips) {
      if (counts.containsKey(ip)) {
        counts.put(ip, counts.get(ip) + 1);
      } else {
        counts.put(ip, 1);
      }
    }
    return iPsMostVisits(counts);
  }

}
