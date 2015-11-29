import java.util.ArrayList;
import java.util.Collections;

public class Chromosome implements Comparable<Chromosome> {
    protected TSP tsp;
    protected double fitness;
    protected boolean isFitnessOutdated;
    protected ArrayList<Integer> genes;

    public Chromosome(TSP tsp) {
        this.tsp = tsp;
        this.genes = this.generateRandomGenes();
        this.isFitnessOutdated = true;
    }

    public Chromosome(TSP tsp, ArrayList<Integer> genes) {
        this.tsp = tsp;
        this.genes = new ArrayList<Integer>();
        for (Integer gene : genes) {
            this.genes.add(gene);
        }
        this.isFitnessOutdated = true;
    }

    public ArrayList<Integer> getGenes() {
        return this.genes;
    }

    public TSP getTSP() {
        return this.tsp;
    }

    public int length() {
        return this.genes.size();
    }

    @Override
    public int compareTo(Chromosome another) {
        return Double.compare(another.getFitness(), this.getFitness());
    }

    public final double getFitness() {
        if (this.isFitnessOutdated) {
            this.fitness = this.fitnessFunction();
        }
        return this.fitness;
    }

    protected double fitnessFunction() {
        int n = this.tsp.getNumberOfCities();
        double fitness = this.tsp.getDistanceBetween((int)this.genes.get(0), (int)this.genes.get(n - 1));
        for (int i = 0; i < n - 1; i++) {
            fitness += this.tsp.getDistanceBetween((int)this.genes.get(i), (int)this.genes.get(i + 1));
        }
        return 1.0 / fitness;
    }
 
    protected ArrayList<Integer> generateRandomGenes() {
        int n = tsp.getNumberOfCities();
        genes = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            genes.add(i);
        }
        Collections.shuffle(genes);
        return genes;
    }

    /**
     * Generate a deep copy of the chromosome.
     */
    public Chromosome clone() {
        return new Chromosome(this.tsp, this.genes);
    }
}
