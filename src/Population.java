import java.util.ArrayList;
import java.util.Collections;

public final class Population {
    private int size;
    private int numGeneration;
    private ArrayList<Chromosome> chromosomes;

    public Population(int size) {
        this.size = size;
        this.numGeneration = 0;
        this.chromosomes = new ArrayList<Chromosome>();
    }

    public int size() {
        return this.size;
    }

    public int getNumberOfGeneration() {
        return this.numGeneration;
    }

    public Chromosome get(int index) {
        return this.chromosomes.get(index);
    }

    public ArrayList<Chromosome> getChromosomes() {
        return this.chromosomes;
    }

    public void addChromosome(Chromosome chromosome) {
        this.chromosomes.add(chromosome);
    }

    public void addChromosomes(ArrayList<Chromosome> chromosomes) {
        this.chromosomes.addAll(chromosomes);
    }

    public boolean isFull() {
        return chromosomes.size() >= this.size;
    }

    public Chromosome getFittestChromosome() {
        Chromosome theFittest = this.chromosomes.get(0);
        for (int i = 1; i < this.chromosomes.size(); i++) {
            if (this.chromosomes.get(i).getFitness() > theFittest.getFitness()) {
                theFittest = this.chromosomes.get(i);
            }
        }
        return theFittest;
    }

    public ArrayList<Chromosome> getElitistChromosomes(int num) {
        Collections.sort(this.chromosomes);
        ArrayList<Chromosome> result = new ArrayList<Chromosome>();
        for (int i = 0; i < Math.min(num, this.chromosomes.size()); i++) {
            result.add(this.chromosomes.get(i));
        }
        return result;
    }

    public void iterate(ArrayList<Chromosome> newGeneration) {
        this.numGeneration += 1;
        this.chromosomes = newGeneration;
    }
}
