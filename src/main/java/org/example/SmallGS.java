package org.example;
import java.util.*;

public class SmallGS  extends GasStation{

    Random rand = new Random();
    private int fulledCars = 0;
    private List<FillingColumn> fillingColumns;
    private List<Car> carQueue;


    public SmallGS(String daytime, double fullness) {
        super(daytime, fullness);
    }

    @Override
    protected void colFactory(int num) {
        List<FillingColumn> res = new ArrayList<>();
        for(int i = 0; i < num; i++){
            FillingColumn newOne = new FillingColumn();
            res.add(newOne);
        }
        setFillingColumns(res);
    }

    @Override
    protected void carFactory(int num) {
        List<Car> res = new ArrayList<>();
        int k = 0;
        for(int i = 0; i < num; i++){
            k += 1;
            if (k % 5 == 0){
                Car truck = new Truck(rand.nextDouble(150.0), rand.nextDouble(140.99));
                res.add(truck);
            } else{
                Car car = new PassengerCar(rand.nextDouble(55.0), rand.nextDouble(50.99));
                res.add(car);
            }
        }
        setCarQueue(res);
    }

    @Override
    protected void working() {
        // распределение машин в очереди по колонкам
        for (Car car: carQueue) {
            boolean done = false;
            int maxi_car = 3;
            while (!done) {
                for (FillingColumn col : fillingColumns) {
                    if (col.getCarQue().size() < maxi_car) {
                        col.setCarQue(car);
                        done = true;
                        fulledCars += 1;
                        break;
                    }
                }
                maxi_car += 1;
            }
        }


        double timePassed = 0.0;


        for (FillingColumn col: fillingColumns) {
            List<Car> cur = col.getCarQue();
            for (Car car: cur) {
                timePassed += col.filling(car, fullness);
                fullness -= car.fuelling();
            }
        }

        //info(timePassed, fulledCars);

    }


    @Override
    protected void info() {

    }

    public void setFillingColumns(List<FillingColumn> fillingColumns) {
        this.fillingColumns = fillingColumns;
    }

    public void setCarQueue(List<Car> carQueue) {
        this.carQueue = carQueue;
    }
}
