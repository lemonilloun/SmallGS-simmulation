package org.example;

public class Truck  extends Car{
    private double tankVolume = 150.0;

    @Override
    protected double fuelling() {
        if (tankFullness + neededFuel <= tankVolume){
            return neededFuel;
        }
        return 0.0;
    }

    public Truck(double tankFullness, double neededFuel) {
        super(tankFullness, neededFuel);
        timeToRefuel = 3.0;
    }

}
