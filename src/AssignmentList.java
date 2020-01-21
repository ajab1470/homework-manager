/**
 * File: AssignmentList.java
 * Author: Ashley Beckers
 * Description: organizes the assignments on a text file of the correct format based on assignments' natural ordering
 *   (see Assignment::compareTo). Contains functionality to add, remove, or change assignments in the file
 *   Note: "top" assignment refers to the assignment of the highest priority (least according to natural ordering)
 *   Note: as of current version, the correct format of the text file is as follows:
 *      1. one assignment per line containing ONLY the attributes of the assignment listed in the correct order (see
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
     * makes the assignmentList so far from the file's current contents
     * @throws IOException from Files::readAllLines
     */
    private void makeList() throws IOException {
        //TODO update to match file format if necessary
        List<String> lines = Files.readAllLines(file.toPath());
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i).strip();
            //see Assignment::toString
            String[] elements = line.split("\\s+", 5);
            String name = elements[0];
            String assigned = elements[1];
            String due = elements[2];
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
     * accesses the top assignment without being able to change it
     * @return the string representation of the top assignment
     */
    public String top() {
        return assignmentList.get(0).toString();
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
        for (Assignment assignment : assignmentList) {
            lines.add(assignment.toString());
        }
        Files.write(file.toPath(), lines);
    }

    /**
     * searches the assignment list for a specific assignment
     * Note: please do not change any of the assignments returned
     * @param name the name of the assignment to search for
     * @return all assignments in the list that contain that name
     */
    public List<Assignment> getAssignment(String name) {
        List<Assignment> assignments = new ArrayList<>();
        for (Assignment assignment : assignmentList) {
            //if this assignment's name contains the name (non case sensitive)
            if (assignment.getName().toUpperCase().contains(name.toUpperCase())) {
                assignments.add(assignment);
            }
        }
        return assignments;
    }

    /**
     * alter an assignment in the list and update list order
     * @pre assignment is in the list
     * @param assignment the assignment to adjust
     * @param date the new due date of that assignment
     */
    public void push(Assignment assignment, String date) {
        assignment.setDue(date);
        assignmentList.remove(assignmentList.indexOf(assignment));
        add(assignment);
    }
}
