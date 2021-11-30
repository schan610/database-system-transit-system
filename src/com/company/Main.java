package com.company;
import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lab4",
                "lab4", "lab4password");
        Statement st = con.createStatement();

        String userInput = displayPrompt();

        while (!userInput.equals("8")) {
            switch (userInput) {
                case "1" -> {
                    one(st);
                    userInput = displayPrompt();
                }
                case "2" -> {
                    two(st);
                    userInput = displayPrompt();
                }
                case "3" -> {
                    three(st);
                    userInput = displayPrompt();
                }
            }

        }

        st.close();
        con.close();
        System.exit(0);
    }

    public static void one(Statement st) throws SQLException {
        Scanner input1 = new Scanner(System.in);
        Scanner input2 = new Scanner(System.in);
        Scanner input3 = new Scanner(System.in);

        System.out.println("Input start location: ");
        String startTime = input1.nextLine();
        System.out.println("Input destination location: ");
        String destination= input2.nextLine();
        System.out.println("Input date (YYYY-MM-DD format): ");
        String date= input3.nextLine();

        String sql =
                "SELECT  T.ScheduledStartTime, T.ScheduledArrivalTime, T.DriverName, T.BusID\n" +
                "FROM tripoffering as T, trip\n" +
                "WHERE trip.TripNumber = T.TripNumber AND trip.StartLocationName = '"+startTime+"' AND \n" +
                "\ttrip.DestinationName = '" +destination+"' AND T.Date = '"+date+"'";
        ResultSet rs = st.executeQuery(sql);

        String format = "%s\t| %s\t| %s\t| %s";
        System.out.printf((format) + "%n", "ScheduledStartTime", "ScheduledArrivalTime", "DriverName", "BusID");
        while (rs.next()) {
            String scheduledStartTime = rs.getString("ScheduledStartTime");
            String scheduledArrivalTime = rs.getString("ScheduledArrivalTime");
            String driverName = rs.getString("DriverName");
            String busID = rs.getString("BusID");

            System.out.printf((format) + "%n", scheduledStartTime+"", scheduledArrivalTime+"", driverName, busID);
        }
    }

    public static void two(Statement st) throws SQLException {
            String userInput = displayTwoPrompt();
            switch (userInput) {
                case "A" -> {
                    Scanner input1 = new Scanner(System.in);
                    Scanner input2 = new Scanner(System.in);
                    Scanner input3 = new Scanner(System.in);

                    System.out.println("Enter Trip#: ");
                    int tripNo = input1.nextInt();
                    System.out.println("Enter date  (YYYY-MM-DD format): ");
                    String date = input2.nextLine();
                    System.out.println("Enter scheduled start time:  ");
                    String startTime = input3.nextLine();

                    String sql = "DELETE FROM tripoffering T " +
                            "WHERE T.TripNumber = " + tripNo + " AND T.Date = '" + date + "' " +
                            "AND T.ScheduledStartTime = '" + startTime + "'";
                    st.executeUpdate(sql);

                    System.out.println("Successfully deleted trip number: " + tripNo);
                }
                case "B" -> {
                    Scanner loop = new Scanner(System.in);
                    String addMore = "Y";
                    while (!addMore.equals("N")) {
                        Scanner input1 = new Scanner(System.in);
                        Scanner input2 = new Scanner(System.in);
                        Scanner input3 = new Scanner(System.in);
                        Scanner input4 = new Scanner(System.in);
                        Scanner input5 = new Scanner(System.in);
                        Scanner input6 = new Scanner(System.in);

                        System.out.println("Enter Trip#: ");
                        int tripNo = input1.nextInt();
                        System.out.println("Enter Date: ");
                        String date = input2.nextLine();
                        System.out.println("Enter start time: ");
                        String startTime = input3.nextLine();
                        System.out.println("Enter arrival time: ");
                        String arrivalTime = input4.nextLine();
                        System.out.println("Enter driver name: ");
                        String driver = input5.nextLine();
                        System.out.println("Enter bus ID: ");
                        int busID = input6.nextInt();

                        String sql = "INSERT INTO tripoffering \n" +
                                "(`TripNumber`, `Date`, `ScheduledStartTime`, `ScheduledArrivalTime`, `DriverName`, `BusID`) \n" +
                                "VALUES ('" + tripNo + "','" + date + "','" + startTime + "','" + arrivalTime + "','" + driver + "','" + busID + "')";
                        st.executeUpdate(sql);

                        System.out.println("Successfully added trip number: " + tripNo);
                        System.out.println("Add more? (Y or N): ");
                        addMore = loop.nextLine();
                    }
                }
                case "C" -> {
                    Scanner update = new Scanner(System.in);
                    Scanner input1 = new Scanner(System.in);
                    Scanner input2 = new Scanner(System.in);
                    Scanner input3 = new Scanner(System.in);

                    System.out.println("Updated driver name: ");
                    String driver = update.nextLine();
                    System.out.println("Enter Trip#: ");
                    int tripNo = input1.nextInt();
                    System.out.println("Enter Date: ");
                    String date = input2.nextLine();
                    System.out.println("Enter start time: ");
                    String startTime = input3.nextLine();

                    String sql = "UPDATE tripoffering\n" +
                            "SET DriverName = '" + driver + "'\n" +
                            "WHERE TripNumber = " + tripNo + " AND Date  = '" + date + "' \n" +
                            "AND ScheduledStartTime = '" + startTime + "'";
                    st.executeUpdate(sql);
                    System.out.println("Driver name changed to " + driver + " successfully.");
                }
                case "D" -> {
                    Scanner update = new Scanner(System.in);
                    Scanner input1 = new Scanner(System.in);
                    Scanner input2 = new Scanner(System.in);
                    Scanner input3 = new Scanner(System.in);

                    System.out.println("Updated bus ID: ");
                    String busID = update.nextLine();
                    System.out.println("Enter Trip#: ");
                    int tripNo = input1.nextInt();
                    System.out.println("Enter Date: ");
                    String date = input2.nextLine();
                    System.out.println("Enter start time: ");
                    String startTime = input3.nextLine();

                    String sql = "UPDATE tripoffering\n" +
                            "SET BusID = '" + busID + "'\n" +
                            "WHERE TripNumber = " + tripNo + " AND Date  = '" + date + "' \n" +
                            "AND ScheduledStartTime = '" + startTime + "'";
                    st.executeUpdate(sql);
                    System.out.println("Bus ID changed to " + busID + " successfully.");
            }
        }

    }

    public static void three(Statement st) throws SQLException {
        Scanner input1 = new Scanner(System.in);
        System.out.println("Enter trip number: ");
        int tripNo = input1.nextInt();

        String sql = "SELECT T.TripNumber, T.StopNumber, T.SequenceNumber, T.DrivingTime\n" +
                "FROM tripstopinfo as T, trip\n" +
                "WHERE trip.TripNumber = T.TripNumber AND T.TripNumber = "+tripNo;
        ResultSet rs = st.executeQuery(sql);

        String format = "%s\t| %s\t| %s\t| %s";
        System.out.printf((format) + "%n", "TripNumber", "StopNumber", "SequenceNumber", "DrivingTime");
        while (rs.next()) {
            String tripNumber = rs.getString("TripNumber");
            String stopNumber = rs.getString("StopNumber");
            String SequenceNumber = rs.getString("SequenceNumber");
            String DrivingTime = rs.getString("DrivingTime");

            System.out.printf((format) + "%n", tripNumber+"", stopNumber+"", SequenceNumber, DrivingTime);
        }
    }

    public static String displayPrompt() {
        Scanner kb = new Scanner(System.in);
        System.out.println();
        System.out.println("1. Display the schedule of all trips for a given StartLocationName and Destination Name");
        System.out.println("2. Edit table");
        System.out.println("3. Display the stops of a given trip.");
        System.out.println("8. Exit");
        System.out.println("Input the number to do the transaction (i.e: 2): ");
        return kb.nextLine();
    }

    public static String displayTwoPrompt() {
        Scanner kb = new Scanner(System.in);
        System.out.println ("A. Delete a trip");
        System.out.println ("B. Add a set of trip offerings");
        System.out.println ("C. Change driver given trip offering");
        System.out.println ("D. Change bus given trip offering");
        System.out.println ("E. Return (or any other key)");
        return kb.nextLine();
    }
}
