package org.example;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        GasStation station = new SmallGS("day", 1000);
        station.colFactory(2);
        Random rand = new Random();
        Thread carGenerator = new Thread(() -> {
            double x = ((SmallGS) station).getFullness();
            while (x >0){
                station.carFactory(rand.nextInt(5));
                station.working();
                try {
                    Thread.sleep(10000);
                    station.info();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        carGenerator.start();

    }
}