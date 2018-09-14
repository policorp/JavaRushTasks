package com.javarush.task.task20.task2027;

import java.util.ArrayList;
import java.util.List;

/* 
Кроссворд
*/
public class Solution {
    public static void main(String[] args) {
//        int[][] crossword = new int[][]{
//                {'f', 'd', 'e', 'r', 'l', 'k'},
//                {'u', 's', 'a', 'm', 'e', 'o'},
//                {'l', 'n', 'g', 'r', 'o', 'v'},
//                {'m', 'l', 'p', 'r', 'r', 'h'},
//                {'p', 'o', 'e', 'e', 'j', 'j'}
//        };
        int[][] crossword = new int[][]{
                {'s', 'm', 'e', 'm', 'a', 'h'},
                {'h', 's', 'v', 'h', 'e', 's'},
                {'o', 'n', 'g', 'f', 'o', 'a'},
                {'m', 'l', 'p', 'm', 'r', 'k'},
                {'e', 'm', 'e', 'e', 'a', 'e'}
        };
        System.out.println(detectAllWords(crossword, "home", "same"));
        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        //
        List<Word> list = new ArrayList<>();
        Word bufferWord = null;
        StringBuilder sb = new StringBuilder();
        List<String> ordinalList = new ArrayList<>();
        List<String> reverseList = new ArrayList<>();

        //vertical
        //make an ordinal and reverse lists of all vertical LINES
        ordinalList = new ArrayList<>();
        reverseList = new ArrayList<>();

        for (int x = 0; x < crossword[0].length; x++) {
            for (int y = 0; y < crossword.length; y++) {
                sb.append((char)crossword[y][x]);
            }
            ordinalList.add(sb.toString());
            reverseList.add(sb.reverse().toString());
            sb = new StringBuilder();
        }

        //looking for words inside of LINES
        for (String word: words) {
            //check ordinal list
            for (int x = 0; x < ordinalList.size(); x++) {
                //
                int status = ordinalList.get(x).indexOf(word);
                if (status == -1) {
                    continue;
                }
                else {
                    int startY = status;
                    int startX = x;
                    int endY = startY + word.length();
                    int endX = x;
                    bufferWord = new Word(word);
                    bufferWord.setStartPoint(startX, startY);
                    bufferWord.setEndPoint(endX, endY);
                    list.add(bufferWord);
                }
            }

            //check reverse list
            for (int x = 0; x < reverseList.size(); x++) {
                //
                int status = reverseList.get(x).indexOf(word);
                if (status == -1) {
                    continue;
                }
                else {
                    int startY = crossword.length - status - 1;
                    int startX = x;
                    int endY = crossword.length - status - word.length();
                    int endX = x;
                    bufferWord = new Word(word);
                    bufferWord.setStartPoint(startX, startY);
                    bufferWord.setEndPoint(endX, endY);
                    list.add(bufferWord);
                }
            }
        }//vertical


//        //print buffer lists
//        System.out.println("ordinal:");
//        for (String s: ordinalList) {
//            System.out.println(s);
//        }
//        System.out.println("reverse:");
//        for (String s: reverseList) {
//            System.out.println(s);
//        }

        /*
        //horizontal
        //make an ordinal and reverse lists of all horizontal LINES
        ordinalList = new ArrayList<>();
        reverseList = new ArrayList<>();

        for (int y = 0; y < crossword.length; y++) {
            for (int x = 0; x < crossword[y].length; x++) {
                sb.append((char)crossword[y][x]);
            }
            ordinalList.add(sb.toString());
            reverseList.add(sb.reverse().toString());
            sb = new StringBuilder();
        }

        //looking for words inside of LINES
        for (String word: words) {
            //check ordinal list
            for (int y = 0; y < ordinalList.size(); y++) {
                //
                int status = ordinalList.get(y).indexOf(word);
                if (status == -1) {
                    continue;
                }
                else {
                    int startY = y;
                    int startX = status;
                    int endY = y;
                    int endX = startX + word.length() - 1;
                    bufferWord = new Word(word);
                    bufferWord.setStartPoint(startX, startY);
                    bufferWord.setEndPoint(endX, endY);
                    list.add(bufferWord);
                }
            }

            //check reverse list
            for (int y = 0; y < reverseList.size(); y++) {
                //
                int status = reverseList.get(y).indexOf(word);
                if (status == -1) {
                    continue;
                }
                else {
                    int startY = y;
                    int startX = crossword[0].length - status - 1;
                    int endY = y;
                    int endX = crossword[0].length - status - word.length();
                    bufferWord = new Word(word);
                    bufferWord.setStartPoint(startX, startY);
                    bufferWord.setEndPoint(endX, endY);
                    list.add(bufferWord);
                }
            }
        }//horizontal
        */



        return list;
    }

    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}
