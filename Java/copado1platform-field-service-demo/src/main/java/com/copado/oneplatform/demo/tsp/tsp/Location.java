package com.copado.oneplatform.demo.tsp.tsp;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "name", "address", "x", "y" })
public class Location {
    String name;
    String address;
    double x;
    double y;

    public Location(){}

    public Location(String name, String address, double x, double y){
        this.name = name;
        this.address = address;
        this.x = x;
        this.y = y;
    }

    public double getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public double distanceTo(Location location){
        double xDistance = Math.abs(getX() - location.getX());
        double yDistance = Math.abs(getY() - location.getY());
        return Math.sqrt( (xDistance*xDistance) + (yDistance*yDistance) );
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString(){
        return getY()+","+getX();
    }

    public String getCoords(){
        return getY()+","+getX();
    }
}