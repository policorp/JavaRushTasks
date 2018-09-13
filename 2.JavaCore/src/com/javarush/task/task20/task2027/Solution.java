package com.javarush.task.task20.task2027;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

/* 
Кроссворд
*/
public class Solution {
    public static void main(String[] args) {
        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };
        detectAllWords(crossword, "home", "same");
        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */

        for (Word word: detectAllWords(crossword, "home", "same")) {
            System.out.println(word.toString());
        }
        System.out.println("end");
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        List<Word> list = new ArrayList<>();
        Word word = null;
        StringBuilder stringBuilder = new StringBuilder();
        Integer height = crossword.length; //stroka - x
        Integer width = crossword[0].length; //stlobec - y

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                //current start position crossword[i][j]

                //j++
                for (int k = j; k < width; k++) {
                    /*
                    reshit' problemu preobrazovaniya int to char
                    inache on vmesto bukvi dobavlyaet kod simvola v stringbuilder

                     */
                    String ss = Character.getName(crossword[i][k]);
                    System.out.println(ss);
                    stringBuilder.append(crossword[i][k]);
                    for (String s: words) {
                        if (s.equals(stringBuilder.toString())) {
                            word = new Word(s);
                            word.setStartPoint(i, j);
                            word.setEndPoint(i, k);
                            list.add(word);
                        }
                    }
                }

                //j--
                stringBuilder = new StringBuilder();
                for (int k = j; k >= 0; k--) {
                    stringBuilder.append(crossword[i][k]);
                    for (String s: words) {
                        if (s.equals(stringBuilder.toString())) {
                            word = new Word(s);
                            word.setStartPoint(i, j);
                            word.setEndPoint(i, k);
                            list.add(word);
                        }
                    }
                }

                //i++
                stringBuilder = new StringBuilder();
                for (int m = i; m < height; m++) {
                    stringBuilder.append(crossword[m][j]);
                    for (String s: words) {
                        if (s.equals(stringBuilder.toString())) {
                            word = new Word(s);
                            word.setStartPoint(i, j);
                            word.setEndPoint(m, j);
                            list.add(word);
                        }
                    }
                }

                //i--
                stringBuilder = new StringBuilder();
                for (int m = i; m >= 0; m--) {
                    stringBuilder.append(crossword[m][j]);
                    for (String s: words) {
                        if (s.equals(stringBuilder.toString())) {
                            word = new Word(s);
                            word.setStartPoint(i, j);
                            word.setEndPoint(m, j);
                            list.add(word);
                        }
                    }
                }


            }
        }
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
