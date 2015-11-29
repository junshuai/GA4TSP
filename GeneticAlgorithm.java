import java.util.ArrayList;

public class GeneticAlgorithm {
    private Parameters params;
    private Population population;
    private Selection selection;
    private Crossover crossover;
    private Mutation mutation;
    private TSP tsp;

    public GeneticAlgorithm(TSP tsp, Parameters params, Selection selection, Crossover crossover, Mutation mutation) {
        this.params = params;
        this.selection = selection;
        this.crossover = crossover;
        this.mutation = mutation;
        this.tsp = tsp;

        this.initialize();
    }

    /**
     * Randomly populating the initial population of GA. This can also be used
     * in restarting the evolving with the same parameters and generic
     * operators.
     */
    public void initialize() {
        int size = this.params.populationSize;
        this.population = new Population(size);
        for (int i = 0; i < size; i++) {
            this.population.addChromosome(new Chromosome(this.tsp));
        }
    }

    /**
     * Let the population evolve ONLY ONCE.
     *
     * NOTE: To make population evolve many times in one step. Please see
     * iterate() method following.
     */
    public void evolve() {
        ArrayList<Chromosome> generation = new ArrayList<>(this.params.populationSize);
        int n = (int)(this.params.populationSize * this.params.elitistRate);
        generation.addAll(this.population.getElitistChromosomes(n));

        while (generation.size() < this.params.populationSize) {
            if (Math.random() < this.params.mutationRate) {
               Chromosome mutant = this.mutation.operate(this.selection.select(this.population));
               generation.add(mutant);
            } else {
                Chromosome father = this.selection.select(this.population);
                Chromosome mother = this.selection.select(this.population);
                ArrayList<Chromosome> children = this.crossover.operate(father, mother);
                generation.addAll(children);
            }
            // the size of population is possible to be greater than the
            // parameter populationSize.
        }

        this.population.iterate(generation);
    }

    /**
     * Iterate evolve() to get results of GA. This will return a ArrayList of
     * the fitness of the fittest chromosome in each iteration. So the final
     * result will be the last element of the ArrayList. This method will
     * print the process of iteration.
     *
     * Note: this iterate() method is not incremental, i.e. whenever calling
     * this method, the population will be initialized and restart to evolve.
     * For more custom iterate approach, please use the evolve() method.
     */
    public ArrayList<Double> iterate(int numberOfIteration) {
        ArrayList<Double> results = new ArrayList<>();

        this.initialize();
        for (int i = 0; i < numberOfIteration; i++) { 
            this.evolve(); 
            Chromosome theFittest = this.getFittestChromosome(); 
            Double result = 1.0 / theFittest.getFitness();
            results.add(result); 

            printProcessBar(i+1, numberOfIteration, 20); 
            System.out.print(" " + result); 
        }
        return results;
    }

    /**
     * A function to make iterate() print beautiful process bar.
     */
    private void printProcessBar(int completed, int total, int length) {
        int totalNumberOfBlock = total / length;
        int numberOfFinishedBlock = completed / totalNumberOfBlock;

        System.out.print("\r[");
        for (int i = 0; i < length; i++)
            System.out.print(i < numberOfFinishedBlock? "=" : " ");
        System.out.print("] (" + completed + "/" + total + ")");
    } 

    public Chromosome getFittestChromosome() {
        return this.population.getFittestChromosome();
    }

    public Population getPopulation() {
        return this.population;
    }

    public TSP getTSP() {
        return this.tsp;
    }
}
