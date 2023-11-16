package com.pluralsight.streams;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        try (Scanner scan = new Scanner(System.in)) {
            List<Person> persons = personList();
            List<Person> queryPerson = filterNames(scan, persons);
            displayPerson(scan, queryPerson);
            ageInformation(scan, persons);
        } catch (Exception e) {
            System.out.println("Error Occurred: " + e.getMessage());
        }
    }

    public static List<Person> personList() {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("John", "Doe", 35));
        persons.add(new Person("Jane", "Smith", 28));
        persons.add(new Person("Peter", "Jones", 42));
        persons.add(new Person("Mary", "Brown", 51));
        persons.add(new Person("David", "Williams", 25));
        persons.add(new Person("Susan", "Miller", 32));
        persons.add(new Person("Michael", "Taylor", 46));
        persons.add(new Person("Elizabeth", "Davis", 59));
        persons.add(new Person("William", "Lopez", 38));
        persons.add(new Person("Barbara", "Wilson", 27));
        return persons;
    }

    public static List<Person> filterNames(Scanner scan, List<Person> persons) {
        List<Person> queryPerson = new ArrayList<>();
        System.out.println("Filter by Name:" +
                "\nFirst Name: ");
        String firstName = scan.nextLine();
        System.out.println("\nLast Name: ");
        String lastName = scan.nextLine();
        for (Person person : persons) {
            if ((firstName.isEmpty() || person.getFirstName().equalsIgnoreCase(firstName)) &&
                    (lastName.isEmpty() || person.getLastName().equalsIgnoreCase(lastName))) {
                queryPerson.add(person);
            }
        }
        return queryPerson;
    }

    public static void displayPerson(Scanner scan, List<Person> persons) {
        if (persons.isEmpty()) {
            System.out.println("Error in Search.");
        } else {
            System.out.println("Query person:");
            for (Person person : persons) {
                System.out.println(person);
            }
        }
    }
    private static void ageInformation(Scanner scan, List<Person> persons) {
        int initialAge = 0;
        int oldAge = Integer.MIN_VALUE;
        int youngAge = Integer.MAX_VALUE;
        for (Person person : persons) {
            int age = person.getAge();
            initialAge += age;
            if (age > oldAge) {
                oldAge = age;
            }
            if (age < youngAge) {
                youngAge = age;
            }
        }
        double averageAge = (double) initialAge / persons.size();
        System.out.println("Age Statistics:" +
                "\n\tAverage Age: " + averageAge +
                "\n\tOldest Age: " + oldAge +
                "\n\tYoungest Age: " + youngAge);
    }

}