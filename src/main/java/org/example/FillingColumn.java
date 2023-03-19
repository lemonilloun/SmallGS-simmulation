package org.example;

import java.util.ArrayList;
import java.util.List;

public class FillingColumn {
    private List<Car> carQue = new ArrayList<>();

    public double filling(Car car, double fullness){
        fullness -= car.fuelling();
        return car.getTimeToRefuel();
    }

    public List<Car> getCarQue() {
        return carQue;
    }

    public void setCarQue(Car car) {
        this.carQue.add(car);
    }
}
