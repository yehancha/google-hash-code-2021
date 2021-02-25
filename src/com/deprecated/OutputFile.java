package com.deprecated;

import java.util.ArrayList;

public class OutputFile {
    private ArrayList<Intersection> intersections = new ArrayList<>();

    public ArrayList<Intersection> getIntersections() {
        return intersections;
    }

    public void setIntersections(ArrayList<Intersection> intersections) {
        this.intersections = intersections;
    }

    public static class StreetSchedule {
        private InputFile.Street street;
        private int openDuration;

        public InputFile.Street getStreet() {
            return street;
        }

        public void setStreet(InputFile.Street street) {
            this.street = street;
        }

        public int getOpenDuration() {
            return openDuration;
        }

        public void setOpenDuration(int openDuration) {
            this.openDuration = openDuration;
        }
    }

    public static class Intersection {
        private int id;
        private ArrayList<StreetSchedule> schedules = new ArrayList<>();

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public ArrayList<StreetSchedule> getSchedules() {
            return schedules;
        }

        public void setSchedules(ArrayList<StreetSchedule> schedules) {
            this.schedules = schedules;
        }
    }
}
