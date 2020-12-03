package com.raczkowski.apps;

import java.util.ArrayList;
import java.util.Collection;

public class Garbage {

    public static Collection<String> mapToUppercase(String... names) {
        Collection<String> uppercaseNames = new ArrayList<>();
        for(String name : names) {
            uppercaseNames.add(name.toUpperCase());
        }
        return uppercaseNames;
    }


}
