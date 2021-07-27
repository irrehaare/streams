package com.raczkowski.apps;

import com.raczkowski.apps.model.Person;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Streams {

    public static Collection<String> mapToUppercase(String... names) {
        return Arrays.stream(names)
                .map(String::toUpperCase)
                .collect(Collectors.toList());
    }

    public static int getTotalNumberOfLettersOfNamesLongerThanFive(String... names) {
        Supplier<Integer> defaultValue = ()-> 0;
        return Arrays.stream(names)
                .map(String::length)
                .filter(length -> length > 5)
                .reduce(Integer::sum)
                .orElseGet(defaultValue);
    }

    public static List<String> flattenListOfLists(List<List<String>> collection) {
        return collection.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    public static Person getOldestPerson(List<Person> people) {
        return people.stream()
                .max(Comparator.comparingInt(Person::getAge))
                .orElseThrow();
    }

    // Sum all of the numbers in the list
    public static int calculate(List<Integer> numbers) {
        return numbers.stream()
                .reduce(Integer::sum)
                .orElseThrow();
    }

    // Get the names of all kids under the age of 18
    public static Set<String> getKidNames(List<Person> people) {
        return people.stream()
                .filter(maybeKid -> maybeKid.getAge() < 18)
                .map(Person::getName)
                .collect(Collectors.toSet());
    }

    // Partition these people into adults and kids, you'll need a special collector for this one
    public static Map<Boolean, List<Person>> partitionAdults(List<Person> people) {
        return people.stream()
                .collect(Collectors.groupingBy(person -> person.getAge() > 18));
    }

    // Group these people by nationality
    public static Map<String, List<Person>> groupByNationality(List<Person> people) {
        Map<String, List<Person>> map = new HashMap<>();
        for (Person person : people) {
            if (!map.containsKey(person.getNationality())) {
                map.put(person.getNationality(), new ArrayList<>());
            }
            map.get(person.getNationality()).add(person);
        }
        return map;
    }

    // Return a comma-separated string of all these people's names
    public static String namesToString(List<Person> people) {
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

}
