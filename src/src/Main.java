/**
 * File: Main.java
 * Author: Ashley Beckers
 * Description: Prompts the user for new assignments and writes them to assignments.txt on my personal laptop in the
 *   correct order so I can do them in the best order instead of doing all my fun programming homework first and not
 *   doing any of the other assignments
 */

import java.io.*;

public class Main {
    private static final File FILE = new File("C:/Users/Ashley/Desktop/assignments.txt");
    private static BufferedWriter writer;
    private static BufferedReader reader;

    /**
     * to handle the ioexception that will never happen
     */
    private static void makeStreams() {
        try {
            writer = new BufferedWriter(new FileWriter(FILE));
            reader = new BufferedReader(new FileReader(FILE));
        } catch (IOException e) {
            System.err.println("The file you know exists somehow doesn't exist.");
            System.err.println(e.getMessage());
        }
    }

    /**
     * parses date from a string of format MM/DD into a 4 digit int of format MMDD
     * @param date the string representation of the date
     * @return the int representation of the date
     */
    private static int parseDate (String date) {
        //multiplies the month by 100 and adds it to the day
        return Integer.parseInt(date.substring(0,2)) * 100 + Integer.parseInt(date.substring(3,5));
    }

    public static void main(String[] args) {

    }
}
