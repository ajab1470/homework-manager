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
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;


public class AssignmentList {
    private List<Assignment> assignmentList = new ArrayList<>();
    private File file;

    /**
     * create a new AssignmentList
     * @param path the path to the file to alter
     * @throws IOException from makeList
     */
    public AssignmentList(String path) throws IOException {
        this.file = new File(path);
        //handling the IOException that will never happen
        makeList();
    }

    /**
     * parses date from a string of format MM/DD into a 4 digit int of format MMDD
     * @param date the string representation of the date
     * @return the int representation of the date
     */
    public static int parseDate (String date) {
        //multiplies the month by 100 and adds it to the day
        return Integer.parseInt(date.substring(0,2)) * 100 + Integer.parseInt(date.substring(3,5));
    }

    /**
     * makes the assignmentList so far from the file's current contents
     * @throws IOException from Files::readAllLines
     */
    private void makeList() throws IOException {
        //TODO update to match file format if necessary
        List<String> lines = Files.readAllLines(file.toPath());
        //the first line will be garbage
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
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

    /**
     * marks the top assignment as complete and returns it, removing it from the list of assignments
     * @return the top assignment after successful removal
     */
    public Assignment complete() {
        return assignmentList.remove(0);
    }

    /**
     * writes out the file
     * @throws IOException from Files::write
     */
    public void updateFile() throws IOException {
        List<String> lines = new ArrayList<>();
        //the first line
        lines.add("Name\tDate Due\tDate Assigned\tCourse\tDescription");
        for (Assignment assignment : assignmentList) {
            lines.add(assignment.toString());
        }
        Files.write(file.toPath(), lines);
    }

    /**
     * searches the assignment list for a specific assignment
     * Note: please do not change any of the assignments returned
     * @param name the name of the assignment to search for
     * @return all assignments in the list with that name
     */
    public List<Assignment> getAssignment(String name) {
        List<Assignment> assignments = new ArrayList<>();
        for (Assignment assignment : assignmentList) {
            if (assignment.getName().equals(name)) {
                assignments.add(assignment);
            }
        }
        return assignments;
    }
}
