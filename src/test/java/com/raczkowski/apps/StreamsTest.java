package com.raczkowski.apps;

import com.raczkowski.apps.model.Person;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static com.raczkowski.apps.Streams.*;
import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class StreamsTest {

    @Test
    public void should_map_to_uppercase() {
        assertTrue(mapToUppercase("aaron", "frank", "william", "gilliam")
                .containsAll(asList("AARON", "FRANK", "WILLIAM", "GILLIAM")));

        assertTrue(mapToUppercase("cegeka")
                .contains("CEGEKA"));
    }

    @Test
    public void should_count_letters_of_words_longer_than_five() {
        assertEquals(getTotalNumberOfLettersOfNamesLongerThanFive("william", "jones", "aaron", "seppe", "frank", "gilliam"), 14);
        assertEquals(getTotalNumberOfLettersOfNamesLongerThanFive("aaron"), 0);
    }

    @Test
    public void transformShouldFlattenCollection() {
        List<List<String>> collection = asList(asList("Viktor", "Farcic"), asList("John", "Doe", "Third"));
        List<String> expected = asList("Viktor", "Farcic", "John", "Doe", "Third");

        assertTrue(Streams.flattenListOfLists(collection).containsAll(expected));
    }

    @Test
    public void getOldestPersonShouldReturnOldestPerson() {
        Person sara = new Person("Sara", 4);
        Person viktor = new Person("Viktor", 40);
        Person eva = new Person("Eva", 42);
        List<Person> collection = asList(sara, eva, viktor);
        assertEquals(Streams.getOldestPerson(collection), eva);
    }

    @Test
    public void calculateShouldSumAllNumbers() {
        List<Integer> numbers = asList(1, 2, 3, 4, 5);
        assertEquals(Streams.calculate(numbers), 1 + 2 + 3 + 4 + 5);
    }

    @Test
    public void getKidNameShouldReturnNamesOfAllKidsUnder18() {
        Person sara = new Person("Sara", 4);
        Person viktor = new Person("Viktor", 40);
        Person eva = new Person("Eva", 42);
        Person anna = new Person("Anna", 5);
        List<Person> collection = asList(sara, eva, viktor, anna);
        assertTrue(Streams.getKidNames(collection).containsAll(asList("Sara", "Anna")));
        assertFalse(Streams.getKidNames(collection).containsAll(asList("Viktor", "Eva")));
    }

    @Test
    public void partitionAdultsShouldSeparateKidsFromAdults() {
        Person sara = new Person("Sara", 4);
        Person viktor = new Person("Viktor", 40);
        Person eva = new Person("Eva", 42);
        List<Person> collection = asList(sara, eva, viktor);
        Map<Boolean, List<Person>> result = partitionAdults(collection);

        result.get(true).containsAll(asList(viktor, eva));
        result.get(false).contains(sara);
    }

    @Test
    public void groupByNationalityTest() {
        Person sara = new Person("Sara", 4, "Norwegian");
        Person viktor = new Person("Viktor", 40, "Serbian");
        Person eva = new Person("Eva", 42, "Norwegian");
        List<Person> collection = asList(sara, eva, viktor);
        Map<String, List<Person>> result = groupByNationality(collection);

        result.get("Norwegian").containsAll(asList(sara, eva));
        result.get("Serbian").contains(viktor);
    }

    @Test
    public void toStringShouldReturnPeopleNamesSeparatedByComma() {
        Person sara = new Person("Sara", 4);
        Person viktor = new Person("Viktor", 40);
        Person eva = new Person("Eva", 42);
        List<Person> collection = asList(sara, viktor, eva);

        assertEquals("Names: Sara, Viktor, Eva.", namesToString(collection));
    }


}