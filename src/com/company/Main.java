package com.company;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static String trucksAndCargo(int x1, int x2, int xCargo) {
        String str = null;

        if(Math.abs(xCargo - x1) < Math.abs(xCargo - x2)) {
            str = "1";
        } else if(Math.abs(xCargo - x1) > Math.abs(xCargo - x2)) {
            str = "2";
        }

        return str;
    }

    public static int wordsCount(String str) {
        int cntWords = 1;
        for(int i = 0; i < str.chars().count(); i++) {
            if(Character.isUpperCase(str.charAt(i))) {
                cntWords++;
            }
        }

        return cntWords;
    }


    public static class Truck{
        long id;
        double x; //координата по оси x
        double y; //координата по оси x
        double speed; //скорость грузовика

        public Truck (long id, double x, double y, double speed)
        {
            this.id = id;
            this.x = x;
            this.y = y;
            this.speed = speed;
        }
    }

    public static long findFirstTruck(List<Truck> trucks, int xCargo, int yCargo) {
        /*trucks.add(new Truck(1, 3, 3, 10));
        trucks.add(new Truck(2, 5, 33, 10));*/

        double length = -1;
        long id = -1;
        for(int i = 0; i < trucks.size(); i++) {
            double x = Math.abs(trucks.get(i).x) - Math.abs(xCargo);
            double y = Math.abs(trucks.get(i).y) - Math.abs(yCargo);
            double l = Math.sqrt((Math.pow(x, 2) + Math.pow(y, 2)));

            if(l < length || length == -1)
            {
                length = l;
                id = trucks.get(i).id;
            }
        }


        return id;
    }

    public static class IdToTableNumberService {

        public static Map<Long, Long> trucksToDriver;
        public static Map<Long, String> driversToTableNumber;

        static {
            trucksToDriver = new HashMap<>();
            trucksToDriver.put(Long.valueOf(1), Long.valueOf(1));

            driversToTableNumber = new HashMap<>();
            driversToTableNumber.put(Long.valueOf(1), "TableNumber");
        }

        IdToTableNumberService() {

        }

        public static String getIdToTableNumber (Long id) {
            return driversToTableNumber.get(trucksToDriver.get(id));
        }
    }

    public class TruckService {
        Map<Integer, Instant> idMeasurement = new HashMap<>();

        public void receiveCoordinates(int truckId, int x, int y, Instant measurementTime){
            if(idMeasurement.size() < 0) {
                return;
            }

            if(idMeasurement.get(truckId).getEpochSecond() > measurementTime.getEpochSecond()) {
                return;
            }

            idMeasurement.put(truckId, measurementTime);

            processCoordinates(truckId, x, y, measurementTime);
        }

        public void processCoordinates(int truckId, int x, int y, Instant measurementTime){
            System.out.println("Пришел пакет "+truckId+" "+x+" "+y+" "+ measurementTime);
        }
    }


    public static void main(String[] args) {

        /*System.out.println(trucksAndCargo(33, 76, 50));
        System.out.println(wordsCount("hiiiii"));
        findFirstTruck(new ArrayList<>(), 1, 2);
        System.out.println(IdToTableNumberService.getIdToTableNumber(Long.valueOf(1)));*/
    }
}
