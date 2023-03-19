package org.example;

public class PassengerCar extends Car{
    private double tankVolume = 55.0;

    @Override
    protected double fuelling() {
        if (tankFullness + neededFuel <= tankVolume){
            return neededFuel;
        } else{
            return tankVolume - tankFullness;
        }
    }

    public PassengerCar(double tankFullness, double neededFuel) {
        super(tankFullness, neededFuel);
        timeToRefuel = 2.0;

    }

}
