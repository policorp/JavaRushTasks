package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by m.polyakov on 18.09.2018.
 */


public class Hippodrome {
    private List<Horse> horses;
    public static Hippodrome game;
    public static void main(String[] args) {
        System.out.println("Hello hippodrome!");
        List<Horse> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Horse horse = new Horse("name" + i, 3, 0);
            list.add(horse);
        }
        game = new Hippodrome(list);
    }

    public Hippodrome(List horses) {
        if (horses != null) {
            this.horses = horses;
        }
        else {
            this.horses = new ArrayList<>();
        }
    }

    public void run() {
    }

    public void move() {
    }

    public void print() {
    }

    public List<Horse> getHorses() {
        return horses;
    }
}
