package org.Harel;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Getter
public class Department {
    private String departmentId;
    @Setter private String departmentName;
    private static int nextId = 1;

    /**
     * checks if a department name is valid or not
     * @param departmentName is the department name to verify
     * @return a boolean value whether the department name is valid or not
     */
    private static boolean isDepartmentNameValid(String departmentName) {
        if (departmentName == null || departmentName.isEmpty()) {
            return false;
        }
        String letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < departmentName.length(); i++) {
            char c = departmentName.charAt(i);
            if (!letters.contains(String.valueOf(c)) && c != ' ') {
                return false;
            }
        }
        return true;
    }
    public Department (String departmentName) {
        if (isDepartmentNameValid(departmentName)) {
            this.departmentName = departmentName;
            if (nextId < 10) {
                this.departmentId = "D0" + nextId;
            } else {
                this.departmentId ="D" + nextId;
            }
            nextId++;
        } else {
            this.departmentId = null;
            this.departmentName = null;
        }
    }
}
