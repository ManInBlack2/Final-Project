package org.Liam;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;

@EqualsAndHashCode
@Getter
@Setter
public class Student {
    private String studentId;
    private String studentName;
    private Gender gender;
    private Address address;
    private Department department;
    private ArrayList<Course> registeredCourses;

    private static int nextId = 1;

    public Student(String studentName, Gender gender, Address address, Department department) {
        this.studentId = String.format("S%06d", nextId++);
        this.studentName = studentName;
        this.gender = gender;
        this.address = address;
        this.department = department;
        this.registeredCourses = new ArrayList<>();
    }

    public enum Gender {
        MALE,
        FEMALE
    }

    public boolean registerCourse(Course course) {
        for (Course course1 : this.registeredCourses) {
            if (course.equals(course1)) {
                return false;
            }
        }

        registeredCourses.add(course);
        course.getStudents().add(Student.this);

        for (Assignment assignment : course.getAssignments()) {
            assignment.getScores().add(null);
        }

        return true;
    }

    public boolean dropCourse(Course course) {
        boolean hasCourse = false;
        for (Course course1 : this.registeredCourses) {
            if (course.equals(course1)){
                hasCourse = true;
                break;
            }
        }

        if (hasCourse) {
            for (int i = 0; i < registeredCourses.toArray().length; i++) {
                if (registeredCourses.get(i) == course) {
                    registeredCourses.remove(i);
                    break;
                }
            }
            for (int i = 0; i < course.getStudents().toArray().length; i++) {
                if (course.getStudents().get(i) == Student.this) {
                    course.getStudents().remove(i);
                    break;
                }
            }
        }

        return hasCourse;
    }

    public String toSimplifiedString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", department=" + department +
                '}';
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", gender=" + gender +
                ", address=" + address +
                ", department=" + department +
                ", registeredCourses=" + registeredCourses.stream().map(Course -> Course.toSimplifiedString()).toList() +
                '}';
    }

}
