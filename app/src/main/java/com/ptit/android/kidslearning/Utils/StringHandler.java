package com.ptit.android.kidslearning.Utils;

import java.util.ArrayList;
import java.util.Collections;

public class StringHandler {
    public StringHandler() {
    }

    public ArrayList<Character> stringToChar(String text) {
        char[] a = text.trim().toUpperCase().toCharArray();
        ArrayList<Character> list = new ArrayList<>();
        for (char anA : a) {
            if (anA != ' ') {
                list.add(anA);
            }
        }
        return list;
    }

    /**
     * Random sort
     */
    public ArrayList<Character> randomSort(ArrayList<Character> list) {
        Collections.sort(list);
        return list;
    }

}