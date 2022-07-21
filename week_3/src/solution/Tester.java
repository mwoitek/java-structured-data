package solution;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Tester {

  public void testLogEntry() {
    LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
    System.out.println(le);
    LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
    System.out.println(le2);
  }

  public void testLogAnalyzer() {
    LogAnalyzer la = new LogAnalyzer();
    la.readFile("../log/short-test_log");
    la.printAll();
  }

  public void testUniqueIP() {
    LogAnalyzer la = new LogAnalyzer();
    la.readFile("../log/short-test_log");
    System.out.println("Number of unique IP addresses: " + la.countUniqueIPs());
  }

  public void testPrintAllHigherThanNum() {
    LogAnalyzer la = new LogAnalyzer();
    int num = 300;
    la.readFile("../log/short-test_log");
    System.out.println("num = " + num);
    la.printAllHigherThanNum(num);
  }

  public void testUniqueIPVisitsOnDay() {
    LogAnalyzer la = new LogAnalyzer();
    la.readFile("../log/weblog-short_log");
    String someday = "Sep 14";
    ArrayList<String> uniqueIPs = la.uniqueIPVisitsOnDay(someday);
    System.out.println("someday = " + someday);
    System.out.println("Number of unique IP addresses: " + uniqueIPs.size());
    someday = "Sep 30";
    uniqueIPs = la.uniqueIPVisitsOnDay(someday);
    System.out.println("someday = " + someday);
    System.out.println("Number of unique IP addresses: " + uniqueIPs.size());
  }

  public void testCountUniqueIPsInRange() {
    LogAnalyzer la = new LogAnalyzer();
    la.readFile("../log/short-test_log");
    int low = 200;
    int high = 299;
    System.out.println("low = " + low + ", high = " + high);
    System.out.println("Number of unique IP addresses: " + la.countUniqueIPsInRange(low, high));
    low = 300;
    high = 399;
    System.out.println("low = " + low + ", high = " + high);
    System.out.println("Number of unique IP addresses: " + la.countUniqueIPsInRange(low, high));
  }

  public void testCountVisitsPerIP() {
    LogAnalyzer la = new LogAnalyzer();
    la.readFile("../log/short-test_log");
    HashMap<String, Integer> counts = la.countVisitsPerIP();
    for (String ipAddress : counts.keySet()) {
      System.out.println("IP address: " + ipAddress + ", Count: " + counts.get(ipAddress));
    }
  }

  public void testMostNumberVisitsByIP() {
    LogAnalyzer la = new LogAnalyzer();
    la.readFile("../log/weblog3-short_log");
    HashMap<String, Integer> counts = la.countVisitsPerIP();
    System.out
        .println("Maximum number of visits by a single IP: " + la.mostNumberVisitsByIP(counts));
  }

  public void testIPsMostVisits() {
    LogAnalyzer la = new LogAnalyzer();
    la.readFile("../log/weblog3-short_log");
    HashMap<String, Integer> counts = la.countVisitsPerIP();
    ArrayList<String> ips = la.iPsMostVisits(counts);
    for (String ip : ips) {
      System.out.println(ip);
    }
  }

  public void testIPsForDays() {
    LogAnalyzer la = new LogAnalyzer();
    la.readFile("../log/weblog3-short_log");
    HashMap<String, ArrayList<String>> daysIPsMap = la.iPsForDays();
    for (String day : daysIPsMap.keySet()) {
      System.out.println("Day: " + day);
      System.out.println("IP addresses:");
      for (String ip : daysIPsMap.get(day)) {
        System.out.println(ip);
      }
    }
  }

  public void testDayWithMostIPVisits() {
    LogAnalyzer la = new LogAnalyzer();
    la.readFile("../log/weblog3-short_log");
    HashMap<String, ArrayList<String>> daysIPsMap = la.iPsForDays();
    System.out.println("Day that has the most visits: " + la.dayWithMostIPVisits(daysIPsMap));
  }

  public void testIPsWithMostVisitsOnDay() {
    LogAnalyzer la = new LogAnalyzer();
    la.readFile("../log/weblog3-short_log");
    HashMap<String, ArrayList<String>> daysIPsMap = la.iPsForDays();
    String day = "Sep 30";
    System.out.println("Day: " + day);
    System.out.println("IP addresses:");
    for (String ip : la.iPsWithMostVisitsOnDay(daysIPsMap, day)) {
      System.out.println(ip);
    }
  }

  public static void main(String[] args) {
    Tester t = new Tester();

    System.out.println("Output of 'testLogEntry':");
    t.testLogEntry();
    System.out.println();

    System.out.println("Output of 'testLogAnalyzer':");
    t.testLogAnalyzer();
    System.out.println();

    System.out.println("Output of 'testUniqueIP':");
    t.testUniqueIP();
    System.out.println();

    System.out.println("Output of 'testPrintAllHigherThanNum':");
    t.testPrintAllHigherThanNum();
    System.out.println();

    System.out.println("Output of 'testUniqueIPVisitsOnDay':");
    t.testUniqueIPVisitsOnDay();
    System.out.println();

    System.out.println("Output of 'testCountUniqueIPsInRange':");
    t.testCountUniqueIPsInRange();
    System.out.println();

    System.out.println("Output of 'testCountVisitsPerIP':");
    t.testCountVisitsPerIP();
    System.out.println();

    System.out.println("Output of 'testMostNumberVisitsByIP':");
    t.testMostNumberVisitsByIP();
    System.out.println();

    System.out.println("Output of 'testIPsMostVisits':");
    t.testIPsMostVisits();
    System.out.println();

    System.out.println("Output of 'testIPsForDays':");
    t.testIPsForDays();
    System.out.println();

    System.out.println("Output of 'testDayWithMostIPVisits':");
    t.testDayWithMostIPVisits();
    System.out.println();

    System.out.println("Output of 'testIPsWithMostVisitsOnDay':");
    t.testIPsWithMostVisitsOnDay();
  }

}
