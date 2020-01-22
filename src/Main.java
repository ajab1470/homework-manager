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
     * takes user input from standard in to manage the file using an AssignmentList
     * @param args unused command line arguments
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Keeping Assignments Prioritized");
        try {
            AssignmentList assignmentList = new AssignmentList("C:/Users/Ashley/Desktop/assignments.txt");
            String input = "";
            //command loop
            while (!input.equals("quit")) {
                System.out.print("Enter command (or h for help): ");
                input = scan.nextLine();
                //add an assignment
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
                //edit an assignment's due date
                } else if (input.equals("edit")) {
                    System.out.print("Give the name of the assignment to edit: ");
                    String name = scan.nextLine();
                    //get the assignments it might be
                    List<Assignment> assignments = assignmentList.getAssignment(name);
                    Assignment assignment = null;
                    //find the assignment it is
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
                //mark assignment complete
                } else if (input.equals("complete")) {
                    Assignment assignment = assignmentList.complete();
                    System.out.println("Assignment \"" + assignment.getName() + "\" marked complete. Nice job!");
                //check top assignment
                } else if (input.equals("top")) {
                    String top = assignmentList.top();
                    System.out.println("The top assignment is currently:\n" + top);
                //exit program. called 'quit' instead of 'exit' to avoid confusion with 'edit'
                } else if (input.equals("quit")) {
                    break;
                //if no valid command (or help) is entered, display usage message
                } else {
                    System.out.println("Usage:\nhelp      display this help message\n" +
                            "add       add a new assignment\n" +
                            "edit      edit the due date on an assignment\n" +
                            "top       check which assignment is currently the top\n" +
                            "complete  mark the top assignment as complete\n" +
                            "quit      exit the program\n" +
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
