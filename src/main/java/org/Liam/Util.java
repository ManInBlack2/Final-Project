package org.Liam;

public class Util {
    public static String toTitleCase(String str) {
        String[] words = str.split(" ");

        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].substring(0, 1).toUpperCase() + words[i].substring(1).toLowerCase();
        }

        String str2 = words[0];

        for (int i = 1; i < words.length; i++) {
            str2 += " " + words[i];
        }

        return str2;
    }
}
