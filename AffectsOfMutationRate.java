import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class AffectsOfMutationRate {
    public static void main(String[] args) throws FileNotFoundException {
        //TSP tsp = TSPGenerator.generateTSPFromFile("data/ATT48.txt");
        TSP tsp = TSPGenerator.generateTSPRandomly(50);
        
        Parameters params1 = new Parameters();
        params1.mutationRate = 0.1;
        params1.populationSize = 1000;
        params1.elitistRate = 0.00;
        
        GeneticAlgorithm ga = new GeneticAlgorithm(
                                                   tsp,
                                                   params1,
                                                   new RouletteWheelSelection(),
                                                   new CycleCrossover(),
                                                   new ExchangeMutation());
        
        Parameters params2 = new Parameters();
        params2.mutationRate = 0.2;
        params2.populationSize = 1000;
        params2.elitistRate = 0.00;
        
        GeneticAlgorithm ga2 = new GeneticAlgorithm(
                                                    tsp,
                                                    params2,
                                                    new RouletteWheelSelection(),
                                                    new CycleCrossover(),
                                                    new ExchangeMutation());
        
        
        Parameters params3 = new Parameters();
        params3.mutationRate = 0.3;
        params3.populationSize = 1000;
        params3.elitistRate = 0.00;
        
        GeneticAlgorithm ga3 = new GeneticAlgorithm(
                                                    tsp,
                                                    params3,
                                                    new RouletteWheelSelection(),
                                                    new CycleCrossover(),
                                                    new ExchangeMutation());
        
        Parameters params4 = new Parameters();
        params4.mutationRate = 0.4;
        params4.populationSize = 1000;
        params4.elitistRate = 0.00;
        
        GeneticAlgorithm ga4 = new GeneticAlgorithm(
                                                    tsp,
                                                    params4,
                                                    new RouletteWheelSelection(),
                                                    new CycleCrossover(),
                                                    new ExchangeMutation());
        
        
        Parameters params5 = new Parameters();
        params5.mutationRate = 0.5;
        params5.populationSize = 1000;
        params5.elitistRate = 0.00;
        
        GeneticAlgorithm ga5 = new GeneticAlgorithm(
                                                    tsp,
                                                    params5,
                                                    new RouletteWheelSelection(),
                                                    new CycleCrossover(),
                                                    new ExchangeMutation());
        
        
        test("1", 1000, ga);
        test("2", 1000, ga2);
        test("3", 1000, ga3);
        test("4", 1000, ga4);
        test("5", 1000, ga5);
    }
    
    private static void test(String title, int numIter, GeneticAlgorithm ga) {
        ga.initialize();
        
        System.out.println("\n" + title);
        
        ArrayList<Double> results = new ArrayList<>(numIter);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < numIter; i++) {
            printProcessBar(i+1, numIter, 20);
            ga.evolve();
            Chromosome best = ga.getFittestChromosome();
            System.out.print(" " + 1.0/best.getFitness());
            results.add(1.0/best.getFitness());
        }
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println(" " + (double)totalTime / 1000 + "s");
        
        try {
            PrintWriter writer = new PrintWriter(title + ".res", "UTF-8");
            writer.println(numIter);
            writer.print(results.get(0));
            for (int i = 1; i < numIter; i++)
                writer.print(" " + results.get(i));
            writer.println("");
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        /*
         Chromosome best = ga.getFittestChromosome();
         System.out.print(best.getGenes().get(0));
         for (int j = 1; j < best.getGenes().size(); j++)
         System.out.print("-" + best.getGenes().get(j));
         System.out.println("");
         */
        
    }
    
    private static void printProcessBar(int completed, int total, int length) {
        int totalNumberOfBlock = total / length;
        int numberOfFinishedBlock = completed / totalNumberOfBlock;
        
        System.out.print("\r[");
        for (int i = 0; i < length; i++)
            System.out.print(i < numberOfFinishedBlock? "=" : " ");
        System.out.print("] (" + completed + "/" + total + ")");
    }
}
