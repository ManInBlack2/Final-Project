package org.Liam;

import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();

        Address address = new Address(123, "Street", "Laval", Address.Province.QC, "A1B2C3");
        System.out.println(address);

        Address address1 = new Address(456, "Rue", "Montreal", Address.Province.QC, "D4E5F6");
        System.out.println(address1);

        Department science = new Department("Science");
        System.out.println(science);

        Department math = new Department("Math");
        System.out.println(math);

        Course physics = new Course("Physics", 4, science);
        Course chemistry = new Course("Chemistry", 4, science);

        Student liam = new Student("Liam M", Student.Gender.MALE, address, science);

        liam.registerCourse(physics);
        physics.addAssignment("Assignment01", 100);


        System.out.println(physics.isAssignmentWeightValid());

        System.out.println(physics);


        liam.registerCourse(chemistry);
        chemistry.registerStudent(liam);
        System.out.println(liam);

        physics.generateScores();


    }

}
