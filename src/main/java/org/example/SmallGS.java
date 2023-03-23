package org.example;
import java.util.*;

public class SmallGS  extends GasStation{

    private int dayNum = 1;
    Random rand = new Random();
    private int fulledCars = 0;
    private double totalTime = 0.0;
    private List<FillingColumn> fillingColumns;
    private List<Car> carQueue;

    private double maxiVolume;


    public SmallGS(String daytime, double fullness) {
        super(daytime, fullness);
        maxiVolume = fullness;
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
    protected double working() {
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
        // работа каждой колонки со своей очередью
        for (FillingColumn col: fillingColumns) {
            n+=1;
            System.out.printf(" %-3d | %-3d | %-10s\n", n, col.getCarQue().size(), col.getCarQue());
            List<Car> cur = col.getCarQue();
            for (Car car: cur) {
                timePassed += col.filling(car);
                if (fullness < car.fuelling()){
                    System.out.println("Out of fuel, waiting for a new gas");
                }
                fullness -= car.fuelling();
            }
            col.clearAllCars();
        }

        System.out.println("Среднее время ожидания: " + timePassed/fillingColumns.size());

        double waitingTime = rand.nextDouble(7.0);

        totalTime += timePassed + waitingTime;
        return timePassed + waitingTime;
    }


    @Override
    protected void info() {
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("Общее время работы: " + totalTime + " | " + "Машин обслуженно: " + fulledCars);
        System.out.println("Топлива осталось: " + fullness);
        System.out.println(daytime + " " + dayNum);
        System.out.println("------------------------------------------------------------------------------\n");
    }

    @Override
    protected void refuelligStation() {
        this.fullness = maxiVolume;
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Приехала машина с топливом....");
        System.out.println("Баки с топливом пополнены");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
    }

    public void setFillingColumns(List<FillingColumn> fillingColumns) {
        this.fillingColumns = fillingColumns;
    }

    public void setCarQueue(List<Car> carQueue) {
        this.carQueue = carQueue;
    }

    public double getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(double totalTime) {
        this.totalTime = totalTime;
    }

    public double getFullness(){
        return this.fullness;
    }

    public void setDayNum(int dayNum) {
        this.dayNum = dayNum;
    }
}
