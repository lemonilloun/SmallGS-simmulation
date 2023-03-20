package org.example;

import java.util.List;

public abstract class GasStation {
    protected String daytime;

    public void setDaytime(String daytime) {
        this.daytime = daytime;
    }

    public String getDaytime() {
        return daytime;
    }

    protected double fullness;
    protected abstract double working();
    protected abstract void info();

    protected abstract void refuelligStation();

    public GasStation(String daytime, double fullness) {
        this.daytime = daytime;
        this.fullness = fullness;
    }

    protected abstract void colFactory(int num);
    protected abstract void carFactory(int num);

}
