package org.Liam;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Random;

@Getter
public class Assignment {
    private String assignmentId;
    private String assignmentName;
    private double weight;
    private ArrayList<Integer> scores;

    private static int nextId = 1;

    private void calcAssignmentAvg() {
        int sum = 0;
        for (int score : scores) {
            sum += score;
        }
    }

    private void generateRandomScore() {
        Random random = new Random();
        for (int i = 0; i < scores.toArray().length; i++) {
            int num1 = random.nextInt(0, 11);

            if (num1 == 0) {
                scores.toArray()[i] = random.nextInt(0, 60);
            } else if (num1 == 1 || num1 == 2) {
                scores.toArray()[i] = random.nextInt(60, 70);
            } else if (num1 == 3 || num1 == 4) {
                scores.toArray()[i] = random.nextInt(70, 80);
            } else if (num1 == 5 || num1 == 6 || num1 == 7 || num1 == 8) {
                scores.toArray()[i] = random.nextInt(80, 90);
            } else if (num1 == 9 || num1 == 10) {
                scores.toArray()[i] = random.nextInt(90, 101);
            }
        }

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
