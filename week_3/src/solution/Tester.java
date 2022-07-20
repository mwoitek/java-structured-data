package solution;

import java.util.ArrayList;
import java.util.Date;

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
  }

}
