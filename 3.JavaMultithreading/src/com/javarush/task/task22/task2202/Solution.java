package com.javarush.task.task22.task2202;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Найти подстроку
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getPartOfString("JavaRush - лучший сервис обучения Java."));
//        System.out.println(getPartOfString("Амиго и Диего лучшие друзья!"));
    }

    public static String getPartOfString(String string) {
        if (string == null)
            throw new TooShortStringException();
        string += ' ';
        int fisrtSpace = string.indexOf(' ');
        if (fisrtSpace == -1)
            throw new TooShortStringException();
        int fourthSpace = fisrtSpace;
        for (int i = 0; i < 4; i++) {
            fourthSpace = string.indexOf(' ', fourthSpace + 1);
            if (fourthSpace == -1)
                throw new TooShortStringException();
        }
        return string.substring(fisrtSpace + 1, fourthSpace);
    }

    public static class TooShortStringException extends RuntimeException {
    }
}
