package com.javarush.task.task22.task2201;

/* 
Строки нитей или строковые нити? Вот в чем вопрос
*/
public class Solution {
    public static void main(String[] args) {
        new Solution();
    }

    public static final String FIRST_THREAD_NAME = "1#";
    public static final String SECOND_THREAD_NAME = "2#";

    private Thread thread1;
    private Thread thread2;
    private Thread thread3;

    public Solution() {
//        getPartOfString("A\tB\tC\tD\tE\tF\tG\tH\tI", FIRST_THREAD_NAME);
//        getPartOfString("J\tK\tL\tM\tN\tO\tP\tQ\tR\tS\tT\tU\tV\tW\tX\tY\tZ", SECOND_THREAD_NAME);
//        getPartOfString("\t\t", "3#");
        initThreads();
    }

    protected void initThreads() {
        this.thread1 = new Thread(new Task(this, "A\tB\tC\tD\tE\tF\tG\tH\tI"), FIRST_THREAD_NAME);
        this.thread2 = new Thread(new Task(this, "J\tK\tL\tM\tN\tO\tP\tQ\tR\tS\tT\tU\tV\tW\tX\tY\tZ"), SECOND_THREAD_NAME);
        this.thread3 = new Thread(new Task(this, "\t\t"), "3#");

        Thread.setDefaultUncaughtExceptionHandler(new ThisUncaughtExceptionHandler());

        this.thread1.start();
        this.thread2.start();
        this.thread3.start();
    }

    public synchronized String getPartOfString(String string, String threadName) {
        int start = 0;
        int end = 0;
        StringBuilder sb;

        try {
            sb = new StringBuilder(string);
            start = sb.indexOf("\t") + 1;
            sb = new StringBuilder(sb.substring(start));
            end = string.length() - (sb.reverse().indexOf(("\t")) + 1);
//            System.out.printf("string =>%s<, start = %d, end = %d, length=%d\n", string, start, end, string.length());
//            System.out.printf("mod string =>%s<, length=%d<\n\n", string.substring(start, end), string.substring(start, end).length());
            return string.substring(start, end);
        }
        catch (TooShortStringFirstThreadException e) {
            e.printStackTrace();
        }
        catch (TooShortStringSecondThreadException e) {
            e.printStackTrace();
        }
        catch (RuntimeException e) {
            e.printStackTrace();
        }
        return null;
    }
}
