package com.pluralsight.streams;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Program {
    public static void main(String[] args) {
        try (Scanner scan = new Scanner(System.in)) {
            List<Person> persons = personList();
            List<Person> queryPerson = filterNamesStream(scan, persons);
            displayPerson(scan, queryPerson);
            ageInformationStream(scan, persons);
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

    public static List<Person> filterNamesStream(Scanner scan, List<Person> persons) {
        List<Person> queryPerson = new ArrayList<>();
        System.out.println("Filter by Name:" +
                "\nFirst Name: ");
        String firstName = scan.nextLine();
        System.out.println("\nLast Name: ");
        String lastName = scan.nextLine();
        return persons.stream()
                .filter(person -> (firstName.isEmpty() || person.getFirstName().equalsIgnoreCase(firstName)) &&
                    (lastName.isEmpty() || person.getLastName().equalsIgnoreCase(lastName)))
                .collect(Collectors.toList());
    }

    private static void ageInformationStream(Scanner scan, List<Person> persons) {
        double averageAge = persons.stream()
                .mapToInt(Person::getAge)
                .average()
                .orElse(0.0);
        int oldAge = persons.stream()
                .mapToInt(Person::getAge)
                .max()
                .orElse(0);
        int youngAge = persons.stream()
                .mapToInt(Person::getAge)
                .min()
                .orElse(0);
        System.out.println("\nAverage Age: " + averageAge +
                "\nOldest Age: " + oldAge +
                "\nYoungest Age: " + youngAge);
    }
}
//  Testing out a different approach
//    private static void oldAndYoungAge(List<Person> persons) {
//        OptionalInt oldAge = persons.stream()
//                .mapToInt(Person::getAge)
//                .max();
//        OptionalInt youngAge = persons.stream()
//                .mapToInt(Person::getAge)
//                .min();
//        oldAge.ifPresent(age -> System.out.println("Age of Oldest: " + age));
//        youngAge.ifPresent(age -> System.out.println("Age of Youngest: " + age));
//    }