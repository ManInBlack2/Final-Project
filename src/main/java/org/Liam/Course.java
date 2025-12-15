package org.Liam;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@EqualsAndHashCode
@Getter
@Setter
public class Course {
    private String courseId;
    private String courseName;
    private double credits;
    private Department department;
    private ArrayList<Assignment> assignments;
    private ArrayList<Student> students;

    private static int nextId = 1;

    public Course(String courseName, double credits, Department department) {
        this.courseId = String.format("C-%s-%02d", department.getDepartmentId(), nextId++);
        this.courseName = courseName;
        this.credits = credits;
        this.department = department;
        this.assignments = new ArrayList<>();
        this.students = new ArrayList<>();
    }

    public boolean isAssignmentWeightValid() {
        double sum = 0;

        for (Assignment assignment : assignments) {
            sum += assignment.getWeight();
        }

        return sum == 100;
    }

    public boolean registerStudent(Student student) {
        students.add(student);

        for (Assignment assignment : assignments) {
            assignment.getScores().add(null);
        }

        return true;
    }

    public int[] calcStudentsAverage() {
        int[] averages = new int[students.toArray().length];

        for (int i = 0; i < students.toArray().length; i++) {
            for (Assignment assignment : assignments) {
                averages[i] += (int) (assignment.getScores().toArray().length * (assignment.getWeight() / 100));
            }
        }

        return averages;
    }

    public boolean addAssignment(String assignmentName, double weight) {
        Assignment assignment = new Assignment(assignmentName, weight);

        for (Student student : students) {
            assignment.getScores().add(null);
        }

        assignments.add(assignment);
        return true;
    }

    public Integer[] generateScores() {
        for (int i = 0; i < assignments.toArray().length; i++) {
            assignments.get(i).generateRandomScore();
        }

        Integer[] avgs = new Integer[students.toArray().length];

        for (int i = 0; i < assignments.toArray().length; i++) {
            for (int j = 0; j < avgs.length; j++) {
                avgs[j] += (int)(assignments.get(i).getScores().get(j) * assignments.get(i).getWeight() / 100);
            }
        }

        return avgs;
    }

    public void displayScores() {
        Integer[] avgs = generateScores();

        System.out.printf("Course: %s(%s)\n", courseName, courseId);
        System.out.print("                           ");
        for (int i = 0; i < assignments.toArray().length; i++) {
            System.out.printf("%15s", assignments.get(i).getAssignmentName());
        }
        System.out.print("    Final Score\n");
        for (int i = 0; i < students.toArray().length; i++) {
            System.out.printf("        %-20s", students.get(i).getStudentName());
            for (Assignment assignment : assignments) {
                System.out.printf("%15d", assignment.getScores().get(i));
            }
            System.out.printf("%15d\n", avgs[i]);
        }
        System.out.println("        Average             ");
        for (Assignment assignment : assignments) {
            System.out.printf("%15f", assignment.calcAssignmentAvg());
        }
    }

    public String toSimplifiedString() {
        return "courseId=" + courseId +
                ", courseName=" + courseName +
                ", credits=" + credits +
                ", departmentName" + department +
                '}' + "\n";
    }

    @Override
    public String toString() {
        return "Course{" + "\n" +
                "courseId='" + courseId + '\'' + ", \n" +
                "courseName='" + courseName + '\'' + ", \n" +
                "credits=" + credits + ", \n" +
                "department=" + department + ", \n" +
                "assignments=" + assignments + ", \n" +
                "students=" + students.stream().map(Student -> Student.toSimplifiedString()).toList() +
                '}' + "\n";
    }
}
