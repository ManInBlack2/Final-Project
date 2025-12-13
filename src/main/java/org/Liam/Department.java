package org.Liam;

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

    private static int nextId = 0;

    public Department(String departmentName) {
        if (isDepartmentNameValid(departmentName)) {
            this.departmentId = String.format("D%d", nextId++);
            this.departmentName = departmentName;
        } else {
            this.departmentId = null;
            this.departmentName = null;
        }
    }

    static boolean isDepartmentNameValid(String departmentName) {
        for (char c : departmentName.toCharArray()) {
            if (!Character.isLetter(c) || c != ' ') {
                return false;
            }
        }

        return true;
    }
}
