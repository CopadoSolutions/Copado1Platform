package com.copado.oneplatform.demo.tsp.tsp;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import org.apache.commons.cli.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

@Component
public class Main implements CommandLineRunner {

    private Options options = new Options();

    @Autowired
    FreemarkerKMLBuilder kmlBuilder;

    @Override
    public void run(String...args) throws Exception {

        options.addOption("s", "source", true, "source csv file")
               .addOption("d", "destination", true, "target kml file")
               .addOption("i", "iterations", true, "number of iterations");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse( options, args);
        String source = cmd.getOptionValue("source");
        String destination = cmd.getOptionValue("destination");
        if(source==null || destination == null) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp( "tsp", options );
        } else {
            int iterations = 100;
            if (cmd.hasOption("iterations")) {
                iterations = Integer.parseInt(cmd.getOptionValue("iterations"));
            }

            CsvMapper csvMapper = new CsvMapper();
            ObjectReader objectReader = csvMapper.readerWithTypedSchemaFor(Location.class)
                    .with(csvMapper.schemaFor(Location.class).withColumnSeparator(';'));
            MappingIterator<Location> objectMappingIterator = objectReader
                    .readValues(new FileReader(new File(source)));
            List<Location> list = objectMappingIterator.readAll();
            for (Object aList : list) {
                Locations.addLocation((Location) aList);
            }

            // Initialize population
            Population pop = new Population(50, true);
            System.out.println("Initial distance: " + pop.getBestRoute().getDistance());

            // Evolve population for 100 generations
            pop = GeneticAlgorithm.evolvePopulation(pop);
            for (int i = 0; i < iterations; i++) {
                pop = GeneticAlgorithm.evolvePopulation(pop);
            }

            Route survivor = pop.getBestRoute();
            System.out.println("Final distance: " + survivor.getDistance());

            FileWriter fileWriter = new FileWriter(destination);
            fileWriter.write(kmlBuilder.build(survivor));
            fileWriter.flush();
            fileWriter.close();
        }
    }
}
