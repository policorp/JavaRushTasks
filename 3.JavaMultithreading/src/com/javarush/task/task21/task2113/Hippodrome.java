package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by m.polyakov on 18.09.2018.
 */


public class Hippodrome {
    private List<Horse> horses;
    public static Hippodrome game;
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello hippodrome!");
        List<Horse> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            String name = (i % 2 == 0 ? "Stew" : "Robb") + (i % 3 == 0 ? "boy" : "girl") + i;
            Horse horse = new Horse(name + i, 3, 0);
            list.add(horse);
        }
        game = new Hippodrome(list);
        game.run();
        game.printWinner();
    }

    public Hippodrome(List horses) {
        if (horses != null) {
            this.horses = horses;
        }
        else {
            this.horses = new ArrayList<>();
        }
    }

    public void run() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            this.move();
            this.print();
            Thread.sleep(200);
        }
    }

    public void move() {
        for (Horse horse: this.getHorses()) {
            horse.move();
        }
    }

    public void print() {
        for (Horse horse: this.getHorses()) {
            horse.print();
        }
        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
    }

    public List<Horse> getHorses() {
        return horses;
    }

    public Horse getWinner() {
        Horse winner = new Horse("winner", 3, 0);
        for (Horse horse: horses) {
            if (winner.getDistance() < horse.getDistance())
                winner = horse;
        }
        return winner;
    }

    public void printWinner() {
        System.out.println("Winner is " + getWinner().getName() + "!");
    }
}
