package org.Harel;

public class Util {

    public static String toTitleCase(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        String words[] = str.split(" ");
        String name = "";

        for ( String word : words) {
            if (!word.isEmpty()) {
                String firstLetter = word.substring(0, 1).toUpperCase();
                String otherLetter = word.substring(1).toLowerCase();
                String fullWord = firstLetter + otherLetter;

                name = name + fullWord + " ";
            }
        }
        return name.trim();
    }
}
