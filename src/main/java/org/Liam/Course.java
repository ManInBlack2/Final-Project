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
        for (Student student1 : this.students) {
            if (student.equals(student1)) {
                return false;
            }
        }

        students.add(student);
        student.getRegisteredCourses().add(Course.this);

        for (Assignment assignment : assignments) {
            assignment.getScores().add(null);
        }

        return true;
    }

    public int[] calcStudentsAverage() {
        int[] averages = new int[students.size()];

        for (int i = 0; i < students.size(); i++) {
            for (Assignment assignment : assignments) {
                averages[i] += (int) (assignment.getScores().size() * (assignment.getWeight() / 100));
            }
        }

        return averages;
    }

    public boolean addAssignment(String assignmentName, double weight) {
        Assignment assignment = new Assignment(assignmentName, weight);

        for (Student student : students) {
            assignment.getScores().add(0);
        }

        assignments.add(assignment);
        return true;
    }

    public void generateScores() {
        for (Assignment assignment : assignments) {
            assignment.generateRandomScore();
        }

        int[] avgs = new int[students.size()];

        for (Assignment assignment : assignments) {
            for (int j = 0; j < avgs.length; j++) {
                avgs[j] += (int)(assignment.getScores().get(j) * assignment.getWeight() / 100);
            }
        }

    }

    public void displayScores() {
        System.out.printf("Course: %s(%s)\n", courseName, courseId);
        System.out.print("                           ");
        for (Assignment assignment : assignments) {
            System.out.printf("%15s", assignment.getAssignmentName());
        }
        System.out.print("    Final Score\n");
        for (int i = 0; i < students.size(); i++) {
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
