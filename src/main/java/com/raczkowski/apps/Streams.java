package com.raczkowski.apps;

import com.raczkowski.apps.model.Person;
import jdk.jshell.PersistentSnippet;

import javax.sql.rowset.Predicate;
import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;

public class Streams {

    public static Collection<String> mapToUppercase(String... names) {
        return Arrays.stream(names)
                .map(String::toUpperCase)
                .collect(Collectors.toList());
    }

    public static Collection<String> mapToUppercaseOld(String... names) {
        Collection<String> uppercaseNames = new ArrayList<>();
        for (String name : names) {
            uppercaseNames.add(name.toUpperCase());
        }
        return uppercaseNames;
    }

    public static int getTotalNumberOfLettersOfNamesLongerThanFive(String... names) {
        return Arrays.stream(names)
                .map(String::length)
                .filter(length -> length > 5)
                .reduce(Integer::sum)
                .orElse(0);
    }

    public static List<String> flattenListOfListsOld(List<List<String>> collection) {
        List<String> newCollection = new ArrayList<>();
        for (List<String> subCollection : collection) {
            for (String value : subCollection) {
                newCollection.add(value);
            }
        }
        return newCollection;
    }

    public static List<String> flattenListOfLists(List<List<String>> collection) {
        return collection.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    public static Person getOldestPerson(List<Person> people) {
        return people
                .stream()
                .max(Comparator.comparingInt(Person::getAge))
                .orElse(new Person("", 0));
    }

    public static Person getOldestPersonOld(List<Person> people) {
        Person oldestPerson = new Person("", 0);
        for (Person person : people) {
            if (person.getAge() > oldestPerson.getAge()) {
                oldestPerson = person;
            }
        }
        return oldestPerson;
    }

    public static int calculateOld(List<Integer> numbers) {
        int total = 0;
        for (int number : numbers) {
            total += number;
        }
        return total;
    }

    public static int calculate(List<Integer> numbers) {
        return numbers
                .stream()
                .reduce(0, Integer::sum);
    }

    // Get the names of all kids under the age of 18
    public static Set<String> getKidNamesOld(List<Person> people) {
        Set<String> kids = new HashSet<>();
        for (Person person : people) {
            if (person.getAge() < 18) {
                kids.add(person.getName());
            }
        }
        return kids;
    }

    public static Set<String> getKidNames(List<Person> people) {
        return people
                .stream()
                .filter(person -> person.getAge() < 18)
                .map(Person::getName)
                .collect(Collectors.toSet());
    }

    // Partition these people into adults and kids, you'll need a special collector for this one
    public static Map<Boolean, List<Person>> partitionAdultsOld(List<Person> people) {
        Map<Boolean, List<Person>> map = new HashMap<>();
        map.put(true, new ArrayList<>());
        map.put(false, new ArrayList<>());
        for (Person person : people) {
            map.get(person.getAge() >= 18).add(person);
        }
        return map;
    }

    public static Map<Boolean, List<Person>> partitionAdults(List<Person> people) {
        return people
                .stream()
                .collect(Collectors.groupingBy(person -> person.getAge() >= 18,
                        Collectors.toList()));
    }

    // Group these people by nationality
    public static Map<String, List<Person>> groupByNationalityOld(List<Person> people) {
        Map<String, List<Person>> map = new HashMap<>();
        for (Person person : people) {
            if (!map.containsKey(person.getNationality())) {
                map.put(person.getNationality(), new ArrayList<>());
            }
            map.get(person.getNationality()).add(person);
        }
        return map;
    }

    public static Map<String, List<Person>> groupByNationality(List<Person> people) {
        return people.stream()
                .collect(Collectors.groupingBy(Person::getNationality,
                        Collectors.toList()));
    }

    // Return a comma-separated string of all these people's names
    public static String namesToStringOld(List<Person> people) {
        String label = "Names: ";
        StringBuilder sb = new StringBuilder(label);
        for (Person person : people) {
            if (sb.length() > label.length()) {
                sb.append(", ");
            }
            sb.append(person.getName());
        }
        sb.append(".");
        return sb.toString();
    }

    public static String namesToString(List<Person> people) {
        return people
                .stream()
                .map(Person::getName)
                .collect(Collectors.joining(", ", "Names: ", "."));
    }
}
