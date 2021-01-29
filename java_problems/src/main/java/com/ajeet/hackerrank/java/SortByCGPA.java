
package com.ajeet.hackerrank.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class SortByCGPA {

    public static void main(String[] args) {
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(33, "Rumpa", 3.68));
        studentList.add(new Student(85, "Ashis", 3.85));
        studentList.add(new Student(56, "Samiha", 3.75));
        studentList.add(new Student(19, "Samara", 3.75));
        studentList.add(new Student(18, "Samara", 3.75));
        studentList.add(new Student(22, "Fahim", 3.76));

        Collections.sort(studentList, (o1, o2) -> {
            if (o1.getCgpa() == o2.getCgpa()) {
                if (o1.getFname().equals(o2.getFname())) {
                    return Integer.compare(o1.getId(), o2.getId());
                }
                return o1.getFname().compareTo(o2.getFname());
            }

            return Double.compare(o2.getCgpa(), o1.getCgpa());
        });

        studentList.forEach(System.out::println);

    }
}

class Student {
    private int id;
    private String fname;
    private double cgpa;

    public Student(int id, String fname, double cgpa) {
        super();
        this.id = id;
        this.fname = fname;
        this.cgpa = cgpa;
    }

    public int getId() {
        return id;
    }

    public String getFname() {
        return fname;
    }

    public double getCgpa() {
        return cgpa;
    }

    @Override
    public String toString() {
        return "[" + id +
            " " + fname +
            " " + cgpa +
            ']';
    }
}
