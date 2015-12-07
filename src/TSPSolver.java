import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class TSPSolver {
    public static void main(String[] args) throws FileNotFoundException {
        TSP tsp;
        int numIter = 5000;

        if (args.length < 1) {
            System.out.println("Please input the number of cities or the path name of a data file!");
            return;
        }

        if (args.length >= 2) {
            numIter = Integer.parseInt(args[1]);
        }

        try {
            tsp = TSPGenerator.generateTSPRandomly(Integer.parseInt(args[0]));
        } catch (NumberFormatException e) {
            tsp = TSPGenerator.generateTSPFromFile(args[0]);
        }

        Parameters params = new Parameters();
        params.mutationRate = 0.2;
        params.populationSize = 1000;
        params.elitistRate = 0.001;

        GeneticAlgorithm ga = new GeneticAlgorithm(
            tsp,
            params,
            new RouletteWheelSelection(),
            new PartiallyMappedCrossover(),
            new InversionMutation()
        );

        long startTime = System.currentTimeMillis();

        ArrayList<Double> results = ga.iterate(numIter);

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println(" " + (double)totalTime / 1000 + "s");

        try {
            PrintWriter writer = new PrintWriter("res/last.res", "UTF-8");
            writer.println(tsp);

            Chromosome theFittest = ga.getFittestChromosome();
            writer.print(theFittest.getGenes().get(0) + 1);
            for (int j = 1; j < theFittest.getGenes().size(); j++)
                writer.print(" " + (theFittest.getGenes().get(j) + 1));
            writer.println("");

            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("The result have been saved in file res/last.res");
        System.out.println("You can run \"python scripts/draw_route.py res/last.res\" to draw the result.");
    }
}
