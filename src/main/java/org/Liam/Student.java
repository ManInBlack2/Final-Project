package org.Liam;

import java.util.ArrayList;
import java.util.Arrays;

public class Student {
    private String studentId;
    private String studentName;
    private Gender gender;
    private Address address;
    private Department department;
    private ArrayList<Course> registeredCourses;

    private static int nextId = 0;
}
