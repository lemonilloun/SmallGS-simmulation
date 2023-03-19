package org.example;

public abstract class Car {
    protected double timeToRefuel;
    protected double tankFullness;
    protected double neededFuel;
    protected abstract double fuelling();
    protected double getTimeToRefuel(){
        return timeToRefuel;
    }

    public Car(double tankFullness, double neededFuel) {
        this.tankFullness = tankFullness;
        this.neededFuel = neededFuel;
    }

}
