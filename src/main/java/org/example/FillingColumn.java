package org.example;

import java.util.ArrayList;
import java.util.List;

public class FillingColumn {
    private List<Car> carQue = new ArrayList<>();

    public double filling(Car car){
        return car.getTimeToRefuel();
    }

    public double meanTime(List<Car> cars){
        double mean = 0.0;
        for (Car x: cars) {
            mean += x.getTimeToRefuel();
        }
        return mean;
    }

    public List<Car> getCarQue() {
        return carQue;
    }

    public void setCarQue(Car car) {
        this.carQue.add(car);
    }

    public void clearAllCars(){
        this.carQue.clear();
    }
}

