package com.javarush.task.task22.task2202;

/* 
Найти подстроку
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getPartOfString("JavaRush - лучший сервис обучения Java."));
    }

    public static String getPartOfString(String string) {
        String answer = string;
        int start = 0;
        int end = string.length();
        try {
            //
            start = string.indexOf(" ") + 1;
            for (int i = 0; i < 4; i++) {

            }
            answer = answer.substring(start);
        }
        catch (Exception e) {
            throw new TooShortStringException();
        }
        return answer;
    }

    public static class TooShortStringException extends RuntimeException {
    }
}
