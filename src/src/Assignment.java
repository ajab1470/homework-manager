/**
 * File: Assignment.java
 * Author: Ashley Beckers <ajab1470@gmail.com>
 * Description:
 * Assignment object. Defined by name, date assigned, date due, description
 */

import java.lang.*;

public class Assignment implements Comparable<Assignment> {
    public enum Course {
        AMT {
            @Override
            public String toString() {
                return "AMT";
            }
        },
        COCs {
            @Override
            public String toString() {
                return "COCs";
            }
        },
        SWEN {
            @Override
            public String toString() {
                return "SWEN";
            }
        },
        COOP {
            @Override
            public String toString() {
                return "COOP";
            }
        },
        STATS {
            @Override
            public String toString() {
                return "STATS";
            }
        },
        CHEM {
            @Override
            public String toString() {
                return "CHEM";
            }
        }
    }

    private Course course;
    /**
     * must be 4 digit number of format MMDD
     */
    private int assigned;
    /**
     * must be 4 digit number of format MMDD
     */
    private int due;
    private String name;
    private String description;

    /**
     * Creates a new assignment
     */
    public Assignment(Course course, int assigned, int due, String name, String description) {
        this.course = course;
        this.assigned = assigned;
        this.due = due;
        this.name = name;
        this.description = description;
    }

    /**
     * The assignment got pushed! (I hope)
     *
     * @param due the new due date
     */
    public void setDue(int due) {
        this.due = due;
    }

    /**
     * Assignments are ordered first by date due (earliest), then by date assigned (earliest), and lastly alphabetically
     * by name
     */
    @Override
    public int compareTo(Assignment o) {
        if (o.due == this.due) {
            if (o.assigned == this.assigned) {
                return this.name.compareTo(o.name);
            } else if (this.assigned < o.assigned) {
                return -1;
            } else {
                return 1;
            }
        } else if (this.due < o.due) {
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
            return o.due == this.due && o.assigned == this.assigned && o.name.equals(this.name);
        }
        return false;
    }

    @Override
    public String toString() {
        return name.toUpperCase() + "\t" + (due / 100) + " / " + (due & 100) + "\t"
                + (assigned / 100) + " / " + (assigned % 100) + "\t"
                + (description.length() > 16 ? (description.substring(0, 13) + "...") : description);
    }
}

