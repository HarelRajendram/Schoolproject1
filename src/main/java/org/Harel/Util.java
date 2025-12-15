package org.Harel;

public class Util {
    /**
     * converts each word in a string to title case
     * @param str is the String to convert to title case
     * @return the String separated by space in title case
     */
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
