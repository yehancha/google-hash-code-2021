package com.deprecated;

import java.util.ArrayList;

public class InputFile {
    private int simulationDuration;
    private int bonus;
    private ArrayList<Street> streets = new ArrayList<>();
    private ArrayList<Car> cars = new ArrayList<>();

    public int getSimulationDuration() {
        return simulationDuration;
    }

    public void setSimulationDuration(int simulationDuration) {
        this.simulationDuration = simulationDuration;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public ArrayList<Street> getStreets() {
        return streets;
    }

    public void setStreets(ArrayList<Street> streets) {
        this.streets = streets;
    }

    public ArrayList<Car> getCars() {
        return cars;
    }

    public void setCars(ArrayList<Car> cars) {
        this.cars = cars;
    }

    public static class Street {
        private int startIntersection;
        private int endIntersection;
        private String name;
        private int travelDuration;

        public int getStartIntersection() {
            return startIntersection;
        }

        public void setStartIntersection(int startIntersection) {
            this.startIntersection = startIntersection;
        }

        public int getEndIntersection() {
            return endIntersection;
        }

        public void setEndIntersection(int endIntersection) {
            this.endIntersection = endIntersection;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getTravelDuration() {
            return travelDuration;
        }

        public void setTravelDuration(int travelDuration) {
            this.travelDuration = travelDuration;
        }
    }

    public static class Car {
        private ArrayList<Street> streets = new ArrayList<>();
        private int minDuration;

        public ArrayList<Street> getStreets() {
            return streets;
        }

        public void setStreets(ArrayList<Street> streets) {
            this.streets = streets;
        }

        public int getMinDuration() {
            return minDuration;
        }

        public void setMinDuration(int minDuration) {
            this.minDuration = minDuration;
        }
    }
}
