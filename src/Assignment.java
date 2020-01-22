/**
 * File: Assignment.java
 * Author: Ashley Beckers <ajab1470@gmail.com>
 * Description:
 * Assignment object. Defined by name, date assigned, date due, course, and description
 */

import java.lang.*;

public class Assignment implements Comparable<Assignment> {

    private String course;
    /**
     * must be of format MM/DD
     */
    private String assigned;
    /**
     * must be of format MM/DD
     */
    private String due;
    private String name;
    private String description;

    /**
     * parses date from a string of format MM/DD into a 4 digit int of format MMDD
     * @param date the string representation of the date
     * @return the int representation of the date
     */
    public static int parseDate (String date) {
        //multiplies the month by 100 and adds it to the day
        try {
            return Integer.parseInt(date.substring(0, 2)) * 100 + Integer.parseInt(date.substring(3, 5));
        } catch (StringIndexOutOfBoundsException e) {
            System.err.println("Could not parse date " + date);
            e.printStackTrace();
            System.exit(1);
        }
        return 0;
    }

    /**
     * Creates a new assignment
     */
    public Assignment(String course, String assigned, String due, String name, String description) {
        this.course = course;
        this.assigned = assigned;
        this.due = due;
        this.name = name;
        this.description = description;
    }

    /**
     * The assignment got pushed! (I hope)
     * @param due the new due date
     */
    public void setDue(String due) {
        this.due = due;
    }

    /**
     * returns the name
     * @return the name of this assignment
     */
    public String getName() {
        return name;
    }

    /**
     * Assignments are ordered first by date due (earliest), then by date assigned (earliest), and lastly alphabetically
     * by name
     */
    @Override
    public int compareTo(Assignment o) {
        if (o.due == this.due) {
            if (o.assigned == this.assigned) {
                return this.name.toUpperCase().compareTo(o.name.toUpperCase());
            } else if (parseDate(this.assigned) < parseDate(o.assigned)) {
                return -1;
            } else {
                return 1;
            }
        } else if (parseDate(this.due) < parseDate(o.due)) {
            return -1;
        } else {
            return 1;
        }
    }

    /**
     * assignments are functionally equivalent if they have the same name, date due, and date assigned
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Assignment) {
            Assignment o = (Assignment) obj;
            return parseDate(o.due) == parseDate(this.due) &&
                    parseDate(o.assigned) == parseDate(this.assigned) &&
                    o.name.toUpperCase().equals(this.name.toUpperCase());
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format(
                "%-16s%-8s%-8s%-12s%s", name, assigned, due, course,
                (description.length() > 32 ? (description.substring(0, 29) + "...") : description)
        );
    }
}

