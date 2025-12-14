package org.Liam;

import java.util.ArrayList;

public class Assignment {
    private String assignmentId;
    private String assignmentName;
    private double weight;
    private ArrayList<Integer> scores;

    private static int nextId = 1;

    private void calcAssignmentAvg() {
        int sum = 0;
        for (int score : scores) {
            sum =+ score;
        }
    }

    private void generateRandomScore() {

    }

    @Override
    public String toString() {
        return "Assignment{" +
                "assignmentName='" + assignmentName + '\'' +
                ", assignmentId='" + assignmentId + '\'' +
                ", weight=" + weight +
                '}';
    }
}
