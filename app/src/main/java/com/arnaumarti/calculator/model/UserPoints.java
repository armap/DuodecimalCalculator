package com.arnaumarti.calculator.model;

/**
 * Created by arnau on 24/10/16.
 */

public class UserPoints {
    private String name;
    private String points;

    public UserPoints(String name, String points) {
        this.name = name;
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoints() {
        return points;
    }

}
