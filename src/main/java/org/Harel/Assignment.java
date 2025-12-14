package org.Harel;

import java.util.ArrayList;

public class Assignment {
    private String assignmentId;
    private String assignmentName;
    private double weight;
    private ArrayList<Integer> scores = new ArrayList<>();
    private static int nextId = 1;

    public void calcAssignmentAvg() {
        double sum = 0;
        double count = 0;
        for (Integer score : scores) {
            if (score == null) {
                continue;
            } else {
                sum+= score;
                count++;
            }
        }
        double assignAvg =(sum / count) * 100;
        System.out.println(assignAvg);
    }
    public void generateRandomScore() {


    }
}
