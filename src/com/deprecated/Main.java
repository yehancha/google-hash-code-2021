package com.deprecated;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Main {
    private static String filePath = "/home/yehancha/Documents/hash-code-2021/src/com/deprecated/";

    public static void main(String[] args) {
        process("a");
        process("b");
        process("c");
        process("d");
        process("e");
        process("f");
    }

    private static int process(String dataset) {
        InputFile inputFile = FileHandler.readInputFile(filePath + dataset + ".txt");
        ArrayList<InputFile.Car> cars = inputFile.getCars();
        cars.sort((c1, c2) -> {
            return c1.getMinDuration() - c2.getMinDuration();
        });

        HashMap<Integer, OutputFile.Intersection> intersectionMap = new HashMap<>();
        HashMap<String, Integer> carCountMap = new HashMap<>();

        List<InputFile.Car> carSublist = cars.subList(0, cars.size() * 6 / 10);

        carSublist.forEach(car -> {
            if (car.getMinDuration() > inputFile.getSimulationDuration()) return;

            car.getStreets().forEach(street -> {
                int id = street.getEndIntersection();
                OutputFile.Intersection intersection = intersectionMap.get(id);

                if (intersection == null) {
                    intersection = new OutputFile.Intersection();
                    intersection.setId(id);
                }

                ArrayList<OutputFile.StreetSchedule> schedules = intersection.getSchedules();
                boolean available = false;
                for (int i = 0; i < schedules.size(); i++) {
                    if (schedules.get(i).getStreet().getName().equals(street.getName()))
                        available = true;
                }

                if (!available) {
                    OutputFile.StreetSchedule streetSchedule = new OutputFile.StreetSchedule();
                    streetSchedule.setStreet(street);
                    streetSchedule.setOpenDuration(1);
                    intersection.getSchedules().add(streetSchedule);
                }

                String countKey = "intersection" + intersection.getId() + "street" + street.getName();
                int carCount = carCountMap.getOrDefault(countKey, 0);
                carCountMap.put(countKey, ++carCount);

                intersectionMap.put(intersection.getId(), intersection);
            });
        });

        intersectionMap.values().forEach(intersection -> {
            ArrayList<OutputFile.StreetSchedule> schedules = intersection.getSchedules();

            schedules.sort((s1, s2) -> {
                String countKey1 = "intersection" + intersection.getId() + "street" + s1.getStreet().getName();
                String countKey2 = "intersection" + intersection.getId() + "street" + s2.getStreet().getName();
                return carCountMap.get(countKey2) - carCountMap.get(countKey1);
            });

            schedules.get(0).setOpenDuration(2);
        });

        OutputFile outputFile = new OutputFile();
        outputFile.getIntersections().addAll(intersectionMap.values());

        FileHandler.writeOutputFile(outputFile, filePath + dataset + ".out");

        return 0;
    }
}
