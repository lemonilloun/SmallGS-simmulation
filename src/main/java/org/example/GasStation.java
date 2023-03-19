package org.example;

import java.util.List;

public abstract class GasStation {
    protected String daytime;
    protected double fullness;
    protected abstract void working();
    protected abstract void info();

    public GasStation(String daytime, double fullness) {
        this.daytime = daytime;
        this.fullness = fullness;
    }

    protected abstract void colFactory(int num);
    protected abstract void carFactory(int num);

}
