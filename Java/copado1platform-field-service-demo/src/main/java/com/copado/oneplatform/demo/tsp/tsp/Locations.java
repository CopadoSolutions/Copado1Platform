package com.copado.oneplatform.demo.tsp.tsp;

import java.util.ArrayList;
import java.util.List;

public class Locations {

    private static ArrayList destinations = new ArrayList<Location>();

    public static void addLocation(Location location) {
        destinations.add(location);
    }

    public static Location getLocation(int index){
        return (Location) destinations.get(index);
    }

    public static int numberOfLocation(){
        return destinations.size();
    }

    public static List<Location> getLocations() { return destinations; }
}
