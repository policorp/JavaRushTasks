package com.javarush.task.task20.task2026;

/* 
Алгоритмы-прямоугольники
*/
public class Solution {
    public static void main(String[] args) {
        byte[][] a1 = new byte[][]{
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 1}
        };
        byte[][] a2 = new byte[][]{
                {1, 0, 0, 1},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {1, 0, 0, 1}
        };

        int count1 = getRectangleCount(a1);
        System.out.println("count = " + count1 + ". Должно быть 2");
        int count2 = getRectangleCount(a2);
        System.out.println("count = " + count2 + ". Должно быть 4");
    }

    public static int getRectangleCount(byte[][] a) {
        int size = a.length;
        int rectCounter = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                //search for 1 only
                if ((a[i][j] == -1) || (a[i][j] == 0))
                    continue;

                //start point at i, j
                int width = 0;
                for (int k = i; k < size; k++) {
                    if (a[k][j] == 0)
                        break;
                    width++;
                }

                int height = 0;
                for (int m = j; m < size; m++) {
                    if (a[i][m] == 0)
                        break;
                    height++;
                }

//                System.out.println("i= " + i + ", j=" + j);
//                System.out.println(width + " " + height);

                for (int g = i; g < width + i; g++) {
                    for (int f = j; f < height + j; f++) {
                        a[g][f] = -1;
                    }
                }

                rectCounter++;
            }
        }
        return rectCounter;
    }
}
