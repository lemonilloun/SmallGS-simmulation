package org.example;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        GasStation station = new SmallGS("Day", 1000);
        station.colFactory(1);
        Random rand = new Random();


        Thread carGenerator = new Thread(() -> {
            double x;
            double timer = 0.0;
            double tuner = 0.0;
            int carRandomizer = 5;
            int colRandomizer = 1;
            int dayNum = 1;
            while (timer < 550.0){
                station.carFactory(rand.nextInt(carRandomizer));
                timer += station.working();
                x = ((SmallGS) station).getFullness();

                if(timer >= (tuner + 60.0)){
                    if(station.getDaytime() == "Day"){
                        station.setDaytime("Night");
                        carRandomizer = 3;
                    } else{
                        station.setDaytime("Day");
                        dayNum ++;
                        ((SmallGS) station).setDayNum(dayNum);
                        carRandomizer = 7;
                    }
                    tuner += 60.0;
                }

                try {
                    Thread.sleep(1500);
                    station.info();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if(x < 400.0) {
                    try {
                        Thread.sleep(1000);
                        station.refuelligStation();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if(station.getMeanTimeOfWaiting() >= 5.0){
                    colRandomizer ++;
                } else if (station.getMeanTimeOfWaiting() < 1.0 && colRandomizer > 1) {
                    colRandomizer --;
                }
                station.colFactory(colRandomizer);
            }
        });
        carGenerator.start();

    }
}