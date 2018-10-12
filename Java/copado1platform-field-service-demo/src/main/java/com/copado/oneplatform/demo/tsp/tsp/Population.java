package com.copado.oneplatform.demo.tsp.tsp;

public class Population {

    Route[] route;

    public Population(int populationSize, boolean initialise) {
        route = new Route[populationSize];
        if (initialise) {
            for (int i = 0; i < populationSize(); i++) {
                Route newRoute = new Route();
                newRoute.generateIndividual();
                addRoute(i, newRoute);
            }
        }
    }

    public void addRoute(int index, Route route) {
        this.route[index] = route;
    }

    public Route getRoute(int index) {
        return route[index];
    }

    public Route getBestRoute() {
        Route fittest = route[0];
        for (int i = 1; i < populationSize(); i++) {
            if (fittest.getFitness() <= getRoute(i).getFitness()) {
                fittest = getRoute(i);
            }
        }
        return fittest;
    }

    public int populationSize() {
        return route.length;
    }
}
