package org.example;

public class Truck  extends Car{
    private double tankVolume = 150.0;

    @Override
    protected double fuelling() {
        if (tankFullness + neededFuel <= tankVolume){
            return neededFuel;
        } else{
            return tankVolume - tankFullness;
        }
    }

    public Truck(double tankFullness, double neededFuel) {
        super(tankFullness, neededFuel);
        timeToRefuel = (tankVolume - tankFullness)/20.0;
    }
    @Override
    public String toString(){
        return "Truck";
    }

}
