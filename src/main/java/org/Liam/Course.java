package org.Liam;

import java.util.ArrayList;

public class Course {
    private String courseId;
    private String courseName;
    private double credits;
    private Department department;
    private ArrayList<Assignment> assignments;
    private ArrayList<Student> students;

    private static int nextId = 1;
}
