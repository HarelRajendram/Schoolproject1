package org.Harel;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Random;
@Setter
@Getter
public class Assignment {
    private String assignmentId;
    private String assignmentName;
    private double weight;
    private ArrayList<Integer> scores = new ArrayList<>();
    private static int nextId = 1;

    private double assignmentAvg;

    /** Calculates the average of the assignment score
     * method to get the average score
     */
    public void calcAssignmentAvg() {
        int sum = 0;
        int count = 0;
        for (Integer score : scores) {
            if (score != null) {
                sum += score;
                count++;
            }
        }
        if (count == 0) {
            assignmentAvg = 0.0;
        } else {
            assignmentAvg = sum / (double) count;
        }
    }
    /** generates random scores for all students in an assignment
     * each random score has a range to place the students
     */
    public void generateRandomScore() {
        Random rand = new Random();
        for (int i = 0; i < scores.size(); i++) {
            int p = rand.nextInt(0,11);
            int score;
            switch (p) {
                case 0 -> score = rand.nextInt(0,60);
                case 1, 2 -> score = rand.nextInt(60,70);
                case 3, 4 -> score = rand.nextInt(70,80);
                case 5, 6, 7, 8 -> score = rand.nextInt(80,90);
                case 9, 10 -> score = 90 + rand.nextInt(11);
                default -> score = 0;
            }
            scores.set(i ,score);
        }
    }
    public Assignment (String assignmentName, double weight) {
        this.assignmentId = String.valueOf(nextId++);
        this.assignmentName = assignmentName;
        this.weight = weight;

    }
    @Override
    public String toString() {
        return "Assignment{" +
                "assignmentId='" + assignmentId + '\'' +
                ", assignmentName='" + assignmentName + '\'' +
                ", weight=" + weight +
                '}';
    }
}
