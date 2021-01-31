package com.ajeet.hackerrank.java;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class PriorityQueueExample {

    /**
     * <pre>
     * There are a number of students in a school who wait to be served.
     * Two types of events, ENTER and SERVED, can take place which are described below.
     *
     * ENTER: A student with some priority enters the queue to be served.
     * SERVED: The student with the highest priority is served (removed) from the queue.
     * A unique id is assigned to each student entering the queue.
     * The queue serves the students based on the following criteria (priority criteria):
     *
     * The student having the highest Cumulative Grade Point Average (CGPA) is served first.
     * Any students having the same CGPA will be served by name in ascending case-sensitive alphabetical order.
     * Any students having the same CGPA and name will be served in ascending order of the id.
     * Create the following two classes:
     *
     * The Student class should implement:
     * The constructor Student(int id, String name, double cgpa).
     * The method int getID() to return the id of the student.
     * The method String getName() to return the name of the student.
     * The method double getCGPA() to return the CGPA of the student.
     * The Priorities class should implement the method List<Student> getStudents(List<String> events) to process
     * all the given events and return all the students yet to be served in the priority order.
     *
     * Input Format
     *
     * The first line contains an integer, , describing the total number of events.
     * Each of the  subsequent lines will be of the following two forms:
     *
     * ENTER name CGPA id: The student to be inserted into the priority queue.
     * SERVED: The highest priority student in the queue was served.
     * The locked stub code in the editor reads the input and tests the correctness of the Student and Priorities classes implementation.
     * </pre>
     */
    public static void main(String[] args) {
        String[] events = new String[]{
            "ENTER John 3.75 50",
            "ENTER Mark 3.8 24",
            "ENTER Shafaet 3.7 35",
            "SERVED",
            "SERVED",
            "ENTER Samiha 3.85 36",
            "SERVED",
            "ENTER Ashley 3.9 42",
            "ENTER Maria 3.6 46",
            "ENTER Anik 3.95 49",
            "ENTER Dan 3.95 50",
            "SERVED"
        };

        Priorities priorities = new Priorities();
        List<Student3> students = priorities.getStudents(Arrays.stream(events).collect(Collectors.toList()));

        students.stream().map(Student3::getName).forEach(System.out::println);
    }
}

class Priorities {

    PriorityQueue<Student3> queue = new PriorityQueue<>(
        Comparator.comparing(Student3::getCgpa).reversed()
            .thenComparing(Student3::getName)
            .thenComparing(Student3::getId)
    );

    public List<Student3> getStudents(List<String> events) {
        for (String e : events) {

            String[] data = e.split(" ");

            if (data[0].equals("SERVED")) {
                if (!queue.isEmpty()) {
                    queue.remove();
                }
            } else {
                Student3 student = new Student3(data[1], Double.parseDouble(data[2]), Integer.parseInt(data[3]));
                queue.add(student);
            }
        }

        List<Student3> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            result.add(queue.poll());
        }

        return result;
    }
}

class Student3 {
    String name;
    double cgpa;
    int id;

    public Student3(String name, double cgpa, int id) {
        this.name = name;
        this.cgpa = cgpa;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public double getCgpa() {
        return cgpa;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "[" + cgpa + " " + name + " " + id + "]";
    }
}

