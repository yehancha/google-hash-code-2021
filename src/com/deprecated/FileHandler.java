package com.deprecated;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class FileHandler {
    public static InputFile readInputFile(String fileName) {
        InputFile inputFile = null;

        try {
            inputFile = new InputFile();

            File file = new File(fileName);
            Scanner scanner = new Scanner(file);

            // Reading the first line
            String[] data = scanner.nextLine().split(" ");
            inputFile.setSimulationDuration(Integer.parseInt(data[0]));
            int intersectionCount = Integer.parseInt(data[1]);
            int streetCount = Integer.parseInt(data[2]);
            int carCount = Integer.parseInt(data[3]);
            inputFile.setBonus(Integer.parseInt(data[4]));

            HashMap<String, InputFile.Street> streetMap = new HashMap<>();

            // Reading street lines
            for (int i = 0; i < streetCount; i++) {
                InputFile.Street street = new InputFile.Street();

                data = scanner.nextLine().split(" ");
                street.setStartIntersection(Integer.parseInt(data[0]));
                street.setEndIntersection(Integer.parseInt(data[1]));
                String name = data[2];
                street.setName(name);
                street.setTravelDuration(Integer.parseInt(data[3]));

                inputFile.getStreets().add(street);
                streetMap.put(name, street);
            }

            // Reading car lines
            for (int i = 0; i < carCount; i++) {
                InputFile.Car car = new InputFile.Car();

                data = scanner.nextLine().split(" ");
                int pathCount = Integer.parseInt(data[0]);

                int minDuration = 0;
                for (int j = 1; j < pathCount + 1; j++) {
                    InputFile.Street street = streetMap.get(data[j]);
                    car.getStreets().add(street);
                    minDuration += street.getTravelDuration();
                }

                car.setMinDuration(minDuration);
                inputFile.getCars().add(car);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return inputFile;
    }

    public static void writeOutputFile(OutputFile file, String fileName) {
        try {
            ArrayList<OutputFile.Intersection> intersections = file.getIntersections();

            String outputString = "";
            outputString += intersections.size() + "\n";

            for (OutputFile.Intersection intersection : intersections) {
                ArrayList<OutputFile.StreetSchedule> schedules = intersection.getSchedules();

                outputString += intersection.getId() + "\n";
                outputString += schedules.size() + "\n";

                for (OutputFile.StreetSchedule schedule : schedules) {
                    outputString += schedule.getStreet().getName() + " " + schedule.getOpenDuration() + "\n";
                }
            }

            outputString = outputString.substring(0, outputString.length() - 1);

            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.write(outputString);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
