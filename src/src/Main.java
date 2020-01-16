/**
 * File: Main.java
 * Author: Ashley Beckers
 * Description: Prompts the user for new assignments and writes them to assignments.txt on my personal laptop in the
 * correct order so I can do them in the best order instead of doing all my fun programming homework first and not
 * doing any of the other assignments
 */

import java.io.*;
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
                    int assigned = AssignmentList.parseDate(scan.nextLine());
                    System.out.print("Date due: ");
                    int due = AssignmentList.parseDate(scan.nextLine());
                    System.out.print("Course: ");
                    String course = scan.nextLine();
                    System.out.print("Brief description: ");
                    String description = scan.nextLine();
                    assignmentList.add(new Assignment(course, assigned, due, name, description));
                } else if (input.equals("edit")) {
                    System.out.print("Give the name of the assignment to edit: ");
                    String name = scan.nextLine();
                    //TODO implement
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
                            "quit\texit the program");
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
