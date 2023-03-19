package org.example;
import java.util.*;

public class SmallGS  extends GasStation{

    Random rand = new Random();
    private int fulledCars = 0;
    private double totalTime = 0.0;
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
            if (k % 3 == 0){
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
        System.out.println("\nИнформация по колонкам\n");
        System.out.println( " Col " + "|" + " Num " + "|" + " Queue");
        for (Car car: carQueue) {
            boolean done = false;
            int maxi_car = 1;
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

        int n = 0;
        for (FillingColumn col: fillingColumns) {
            n+=1;
            System.out.printf(" %-3d | %-3d | %-10s\n", n, col.getCarQue().size(), col.getCarQue());
            List<Car> cur = col.getCarQue();
            for (Car car: cur) {
                timePassed += col.filling(car, fullness);
                if (fullness < car.fuelling()){
                    System.out.println("Out of fuel, waiting for a new gas");
                }
                fullness -= car.fuelling();
            }
            col.clearAllCars();
        }

        totalTime += timePassed;
    }


    @Override
    protected void info() {
        System.out.println("---------------------------");
        System.out.println("Общее время работы: " + totalTime + " | " + "Машин обслуженно: " + fulledCars);
        System.out.println("Топлива осталось: " + fullness);
        System.out.println("---------------------------");
    }

    public void setFillingColumns(List<FillingColumn> fillingColumns) {
        this.fillingColumns = fillingColumns;
    }

    public void setCarQueue(List<Car> carQueue) {
        this.carQueue = carQueue;
    }

    public double getFullness(){
        return this.fullness;
    }
}
