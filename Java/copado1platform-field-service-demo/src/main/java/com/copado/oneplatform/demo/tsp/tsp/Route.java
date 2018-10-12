package com.copado.oneplatform.demo.tsp.tsp;

import java.util.ArrayList;
import java.util.Collections;

public class Route {

    private ArrayList route = new ArrayList<Location>();
    private double fitness = 0;
    private double distance = 0;

    public Route(){
        for (int i = 0; i < Locations.numberOfLocation(); i++) {
            route.add(null);
        }
    }

    public Route(ArrayList route){
        this.route = route;
    }

    // Creates a random individual
    public void generateIndividual() {
        // Loop through all our destination Locations and add them to our route
        for (int locationIndex = 0; locationIndex < Locations.numberOfLocation(); locationIndex++) {
            setLocation(locationIndex, Locations.getLocation(locationIndex));
        }
        // Randomly reorder the route
        Collections.shuffle(route);
    }

    // Gets a location from the route
    public Location getLocation(int position) {
        return (Location) route.get(position);
    }

    // Sets a location in a certain position within a route
    public void setLocation(int position, Location location) {
        route.set(position, location);
        // If the route been altered we need to reset the fitness and distance
        fitness = 0;
        distance = 0;
    }

    // Gets the route fitness
    public double getFitness() {
        if (fitness == 0) {
            fitness = 1/getDistance();
        }
        return fitness;
    }

    // Gets the total distance of the route
    public double getDistance(){
        if (distance == 0) {
            double routeDistance = 0;
            // Loop through our route's locations
            for (int locationIndex = 0; locationIndex < routeSize(); locationIndex++) {
                // Get location we're travelling from
                Location fromLocation = getLocation(locationIndex);
                // location we're travelling to
                Location destinationLocation;
                // Check we're not on our route's last location, if we are set our
                // route's final destination location to our starting location
                if(locationIndex+1 < routeSize()){
                    destinationLocation = getLocation(locationIndex+1);
                }
                else{
                    destinationLocation = getLocation(0);
                }
                // Get the distance between the two locations
                routeDistance += fromLocation.distanceTo(destinationLocation);
            }
            distance = routeDistance;
        }
        return distance;
    }

    // Get number of locations on our route
    public int routeSize() {
        return route.size();
    }

    // Check if the route contains a location
    public boolean containsLocation(Location location){
        return route.contains(location);
    }

    @Override
    public String toString() {
        String geneString = "";
        for (int i = 0; i < routeSize(); i++) {
            geneString += getLocation(i)+"\n";
        }
        geneString += getLocation(0)+"\n";
        return geneString;
    }
}
