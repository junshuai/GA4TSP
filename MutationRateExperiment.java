import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class MutationRateExperiment {
    public static void main(String[] args) throws FileNotFoundException {
        TSP tsp = TSPGenerator.generateTSPRandomly(200);

        GeneticAlgorithmGenerator generator = new GeneticAlgorithmGenerator();
        generator.setPopulationSize(1000);
        generator.setElitistRate(0.001);
        generator.setSelection(new RouletteWheelSelection());
        generator.setCrossover(new PartiallyMappedCrossover());
        generator.setMutation(new InversionMutation());

        generator.setMutationRate(0.0);
        GeneticAlgorithm ga1 = generator.generateGA(tsp);

        generator.setMutationRate(0.2);
        GeneticAlgorithm ga2 = generator.generateGA(tsp);

        generator.setMutationRate(0.4);
        GeneticAlgorithm ga3 = generator.generateGA(tsp);

        generator.setMutationRate(0.6);
        GeneticAlgorithm ga4 = generator.generateGA(tsp);

        generator.setMutationRate(0.8);
        GeneticAlgorithm ga5 = generator.generateGA(tsp);

        generator.setMutationRate(1.0);
        GeneticAlgorithm ga6 = generator.generateGA(tsp);

        test("00", 3000, ga1);
        test("02", 3000, ga2);
        test("04", 3000, ga3);
        test("06", 3000, ga4);
        test("08", 3000, ga5);
        test("10", 3000, ga6);
    }

    private static void test(String title, int numIter, GeneticAlgorithm ga) {
        System.out.println("\n" + title);

        long startTime = System.currentTimeMillis();
        ArrayList<Double> results = ga.iterate(numIter);
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;

        System.out.println(" " + (double)totalTime / 1000 + "s");

        try {
            PrintWriter writer = new PrintWriter("res/" + title + ".res", "UTF-8");
            writer.println(numIter);
            writer.print(results.get(0));
            for (int i = 1; i < numIter; i++)
                writer.print(" " + results.get(i));
            writer.println("");
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
