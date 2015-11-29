import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

/**
 * A experiment for comparing differen crossover methods.
 */
public class CrossoverExperiment {
    public static void main(String[] args) throws FileNotFoundException {
        TSP tsp = TSPGenerator.generateTSPRandomly(100);

        GeneticAlgorithmGenerator generator = new GeneticAlgorithmGenerator();
        generator.setPopulationSize(1000);
        generator.setMutationRate(0.2);
        generator.setElitistRate(0.001);
        generator.setSelection(new RouletteWheelSelection());
        generator.setMutation(new InversionMutation());

        generator.setCrossover(new OrderCrossover());
        GeneticAlgorithm gaOX = generator.generateGA(tsp);

        generator.setCrossover(new CycleCrossover());
        GeneticAlgorithm gaCX = generator.generateGA(tsp);

        generator.setCrossover(new PartiallyMappedCrossover());
        GeneticAlgorithm gaPMX = generator.generateGA(tsp);

        test("OX1", 1000, gaOX);
        test("OX2", 1000, gaOX);
        test("OX3", 1000, gaOX);
        test("CX1", 1000, gaCX);
        test("CX2", 1000, gaCX);
        test("CX3", 1000, gaCX);
        test("PMX1", 1000, gaPMX);
        test("PMX2", 1000, gaPMX);
        test("PMX3", 1000, gaPMX);
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
