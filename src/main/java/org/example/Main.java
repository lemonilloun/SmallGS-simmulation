package org.example;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        GasStation station = new SmallGS("Day", 1000);
        station.colFactory(1);
        station.activate(500);

    }
}