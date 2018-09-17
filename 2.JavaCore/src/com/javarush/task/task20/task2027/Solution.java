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
                {'e', 'm', 'e', 'e', 'a', 'e'},
                {'d', 'f', 'h', 'x', 'x', 's'},
                {'e', 'a', 'a', 'm', 'a', 'a'},
                {'d', 'x', 'm', 'x', 'x', 'h'},
                {'h', 'e', 'f', 'e', 'e', 'e'}
        };
        System.out.println(detectAllWords(crossword, "home", "same", "axe"));
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
        StringBuilder sb = null;
        List<String> ordinalList = null;
        List<String> reverseList = null;

        //diagonal right to left
        //make an ordinal and reverse lists of all diagonal LINES
        ordinalList = new ArrayList<>();
        reverseList = new ArrayList<>();
        sb = new StringBuilder();

        //filling ordinal, reverse arrays
        for (int y = crossword.length - 1; y >= 0; y--) {
            int x = 0;
            int dy = y;
            while ((dy < crossword.length) && (x < crossword[0].length)) {
                sb.append((char)crossword[dy][x]);
                dy++;
                x++;
            }
            ordinalList.add(sb.toString());
            reverseList.add(sb.reverse().toString());
            sb = new StringBuilder();
        }
        for (int x = 1; x < crossword[0].length; x++) {
            int y = 0;
            int dx = x;
            while ((dx < crossword[0].length) && (y < crossword.length)) {
                sb.append((char)crossword[y][dx]);
                y++;
                dx++;
            }
            ordinalList.add(sb.toString());
            reverseList.add(sb.reverse().toString());
            sb = new StringBuilder();
        }

        //looking for words inside of LINES
        for (String word: words) {
            //check ordinal list
            for (int i = 0; i < ordinalList.size(); i++) {
                int status = ordinalList.get(i).indexOf(word);
                if (status == -1) {
                    continue;
                } else {
                    //working
                    int startX = i < crossword.length ? 0 + status : i - crossword.length + 1 + status;
                    int startY = i < crossword.length ? crossword.length - i - 1 + status : 0 + status;
                    int endX = startX + word.length() - 1;
                    int endY = startY + word.length() - 1;
//                    //simler version below w/o
//                    if (i < crossword.length) {
//                        //
//                        startX = 0 + status;
//                        startY = crossword.length - i - 1 + status;
//                        endX = startX + word.length() - 1;
//                        endY = startY + word.length() - 1;
//                    } else {
//                        //
//                        startX = i - crossword.length + 1 + status;
//                        startY = 0 + status;
//                        endX = startX + word.length() - 1;
//                        endY = startY + word.length() - 1;
//                    }

                    bufferWord = new Word(word);
                    bufferWord.setStartPoint(startX, startY);
                    bufferWord.setEndPoint(endX, endY);
                    list.add(bufferWord);
                }
            }

            //check reverse list
            for (int i = 0; i < reverseList.size(); i++) {
                int status = reverseList.get(i).indexOf(word);
                if (status == -1) {
                    continue;
                } else {
                    //
                    int startX = i < crossword[0].length ? i - status : crossword[0].length - 1 - status;
                    int startY = i < crossword[0].length ? crossword.length - 1 - status : crossword.length - (i - crossword[0].length) - status - 2;;
                    int endX = startX - word.length() + 1;
                    int endY = startY - word.length() + 1;
                    //simler version below w/o
//                    if ( i < crossword[0].length) {
//                        //
//                        startX = i - status;
//                        startY = crossword.length - 1 - status;
//                        endX = startX - word.length() + 1;
//                        endY = startY - word.length() + 1;
//                    }
//                    else {
//                        //
//                        startX = crossword[0].length - 1 - status;
//                        startY = crossword.length - (i - crossword[0].length) - status - 2;
//                        endX = startX - word.length() + 1;
//                        endY = startY - word.length() + 1;
//                    }

                    bufferWord = new Word(word);
                    bufferWord.setStartPoint(startX, startY);
                    bufferWord.setEndPoint(endX, endY);
                    list.add(bufferWord);
                }
            }
        }

        //diagonal right to left

        //print buffer lists
//        System.out.println("ordinal:");
//        for (String s: ordinalList) {
//            System.out.println(s);
//        }
//        System.out.println("reverse:");
//        for (String s: reverseList) {
//            System.out.println(s);
//        }


        //vertical - working
        //make an ordinal and reverse lists of all vertical LINES
        ordinalList = new ArrayList<>();
        reverseList = new ArrayList<>();
        sb = new StringBuilder();

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

        //horizontal - working
        //make an ordinal and reverse lists of all horizontal LINES
        ordinalList = new ArrayList<>();
        reverseList = new ArrayList<>();
        sb = new StringBuilder();

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

         //diagonal left-to-right - working
        //make an ordinal and reverse lists of all diagonal LINES
        ordinalList = new ArrayList<>();
        reverseList = new ArrayList<>();
        sb = new StringBuilder();

        for (int y = 0; y < crossword.length; y++) {
            int x = 0;
            int dy = y;
            while ((dy >= 0) && (x < crossword[0].length)) {
                sb.append((char)crossword[dy][x]);
                dy--;
                x++;
            }
            ordinalList.add(sb.toString());
            reverseList.add(sb.reverse().toString());
            sb = new StringBuilder();

        }
        for (int x = 1; x < crossword[0].length; x++) {
            int y = crossword.length - 1;
            int dx = x;
            while ((y >=0) && (dx < crossword[0].length)) {
                sb.append((char)crossword[y][dx]);
                y--;
                dx++;
            }
            ordinalList.add(sb.toString());
            reverseList.add(sb.reverse().toString());
            sb = new StringBuilder();
        }

        //looking for words inside of LINES
        for (String word: words) {
            //check ordinal list
            for (int i = 0; i < ordinalList.size(); i++) {
                int status = ordinalList.get(i).indexOf(word);
                if (status == -1) {
                    continue;
                }
                else {
                    //working
                    int startX = i < crossword.length ? 0 + status : i - crossword.length + 1 + status;
                    int startY = i < crossword.length ? i - status : crossword.length - 1 - status;
                    int endX = startX + word.length() - 1;
                    int endY = startY - word.length() + 1;
                    //simler version below w/o
//                    if ( i < crossword.length) {
//                        //
//                        startX = 0 + status;
//                        startY = i - status;
//                        endX = startX + word.length() - 1;
//                        endY = startY - word.length() + 1 ;
//                    }
//                    else {
//                        //
//                        startX = i - crossword.length + 1 + status;
//                        startY = crossword.length - 1 - status;
//                        endX = startX + word.length() - 1;
//                        endY = startY - word.length() + 1;
//                    }

                    bufferWord = new Word(word);
                    bufferWord.setStartPoint(startX, startY);
                    bufferWord.setEndPoint(endX, endY);
                    list.add(bufferWord);
                }
            }

            //check reverse list
            for (int i = 0; i < reverseList.size(); i++) {
                int status = reverseList.get(i).indexOf(word);
                if (status == -1) {
                    continue;
                }
                else {
                    //
                    int startX = i < crossword[0].length ? i - status : crossword[0].length - status - 1;
                    int startY = i < crossword[0].length ? 0 + status : i - crossword[0].length + 1;
                    int endX = startX - word.length() + 1;
                    int endY = startY + word.length() - 1;
                    //simler version below w/o
//                    if ( i < crossword[0].length) {
//                        //
//                        startX = i - status;
//                        startY = 0 + status;
//                        endX = startX - word.length() + 1;
//                        endY = startY + word.length() - 1 ;
//                    }
//                    else {
//                        //
//                        startX = crossword[0].length - status - 1;
//                        startY = i - crossword[0].length + 1;
//                        endX = startX - word.length() + 1;
//                        endY = startY + word.length() - 1;
//                    }

                    bufferWord = new Word(word);
                    bufferWord.setStartPoint(startX, startY);
                    bufferWord.setEndPoint(endX, endY);
                    list.add(bufferWord);
                }
            }
        } //diagonal left-to-right


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
