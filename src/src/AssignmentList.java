/**
 * File: AssignmentList.java
 * Author: Ashley Beckers
 * Description: organizes the assignments on a text file of the correct format based on assignments' natural ordering
 *   (see Assignment::compareTo). Contains functionality to add, remove, or change assignments in the file
 *   Note: "top" assignment refers to the assignment of the highest priority (least according to natural ordering)
 *   Note: as of current version, the correct format of the text file is as follows:
 *      1. one line of any contents before assignments list
 *      2. one assignment per line containing ONLY the attributes of the assignment listed in the correct order (see
 *         Assignment::toString) and separated by a "\t" character
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AssignmentList {
    private List<Assignment> assignmentList = new ArrayList<>();
    private static BufferedWriter writer;
    private static BufferedReader reader;


    public AssignmentList(String path) {
        File file = new File(path);
        //handling the IOException that will never happens
        try {
            writer = new BufferedWriter(new FileWriter(file));
            reader = new BufferedReader(new FileReader(file));
            makeList();
        } catch (IOException e) {
            System.err.println("The file you know exists somehow doesn't exist.");
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    /**
     * parses date from a string of format MM/DD into a 4 digit int of format MMDD
     * @param date the string representation of the date
     * @return the int representation of the date
     */
    private int parseDate (String date) {
        //multiplies the month by 100 and adds it to the day
        return Integer.parseInt(date.substring(0,2)) * 100 + Integer.parseInt(date.substring(3,5));
    }

    /**
     * makes the assignmentList so far from the file's current contents
     * @throws IOException literally never (hopefully)
     */
    private void makeList() throws IOException{
        //the first line will be garbage
        //TODO update to package
        String line = reader.readLine();
        while (line!=null) {
            line = reader.readLine();
            //see Assignment::toString
            String[] elements = line.split("\t");
            String name = elements[0];
            int due = parseDate(elements[1]);
            int assigned = parseDate(elements[2]);
            String course = elements[3];
            String description = elements[4];
            Assignment assignment = new Assignment(course, assigned, due, name, description);
            add(assignment);
        }
    }

    /**
     * add an assignment to the correct place in the assignment list
     * @param assignment the assignment to be added
     */
    public void add(Assignment assignment) {
        for (int i = 0; i < assignmentList.size(); i++) {
            if (assignment.compareTo(assignmentList.get(i))<1) {
                assignmentList.add(i,assignment);
                return;
            }
        }
        assignmentList.add(assignment);
    }
}
