/**
 * File: Main.java
 * Author: Ashley Beckers
 * Description: Prompts the user for new assignments and writes them to assignments.txt on my personal laptop in the
 * correct order so I can do them in the best order instead of doing all my fun programming homework first and not
 * doing any of the other assignments
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    /**
     * takes user input from standard in to manage the file using a priority queue
     * @param args unused command line arguments
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Keeping Ashley's assignments prioritized");
        try {
            AssignmentList assignmentList = new AssignmentList("C:/Users/Ashley/Desktop/assignments.txt");
            String input = "";
            while (!input.equals("quit")) {
                System.out.print("Enter command (or h for help): ");
                input = scan.nextLine();
                if (input.equals("add")) {
                    System.out.print("Assignment name: ");
                    String name = scan.nextLine();
                    System.out.print("Date assigned: ");
                    String assigned = scan.nextLine();
                    System.out.print("Date due: ");
                    String due = scan.nextLine();
                    System.out.print("Course: ");
                    String course = scan.nextLine();
                    System.out.print("Brief description: ");
                    String description = scan.nextLine();
                    assignmentList.add(new Assignment(course, assigned, due, name, description));
                } else if (input.equals("edit")) {
                    System.out.print("Give the name of the assignment to edit: ");
                    String name = scan.nextLine();
                    List<Assignment> assignments = assignmentList.getAssignment(name);
                    Assignment assignment = null;
                    for (Assignment a : assignments) {
                        System.out.println("Is this the correct assignment? (y for yes)\n" + a);
                        String response = scan.nextLine();
                        if (response.equals("y")) {
                            assignment = a;
                            break;
                        }
                    }
                    if (assignment == null) {
                        System.out.println("Assignment not found.");
                    } else {
                        System.out.print("Enter new due date: ");
                        String date = scan.nextLine();
                        assignmentList.push(assignment, date);
                    }
                } else if (input.equals("complete")) {
                    Assignment assignment = assignmentList.complete();
                    System.out.println("Assignment \"" + assignment.getName() + "\" marked complete. Nice job!");
                } else if (input.equals("quit")) {
                    break;
                } else {
                    System.out.println("Usage:\nhelp\tdisplay this help message\n" +
                            "add\tadd a new assignment\n" +
                            "edit\tedit the due date on an assignment\n" +
                            "complete\tmark the top assignment as complete\n" +
                            "quit\texit the program\n" +
                            "Note: All dates must be in the form MM/DD");
                } //if else
            } //loop
            assignmentList.updateFile();
            System.out.println("File updated successfully");
            System.out.println("Exiting program. Keep up the good work!");
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        } //catch
    } //main method
} //class
