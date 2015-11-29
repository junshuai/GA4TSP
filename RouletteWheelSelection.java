import java.util.Arrays;

public final class RouletteWheelSelection implements Selection {
    private double[] probability;
    private int numGeneration;

    public RouletteWheelSelection() {
        this.numGeneration = -1;
    }

    public Chromosome select(Population population) {
        if (this.numGeneration < population.getNumberOfGeneration()) {
            this.update(population);
        }

        int index = Arrays.binarySearch(this.probability, Math.random());
        // Because floating calculation may lose precision, index may be
        // greater than population.size(). In this rare situation, we just need
        // to redraw a new one.
        while (-index > population.size()) {
            index = Arrays.binarySearch(this.probability, Math.random());
        }
        index = index >= 0 ? index : -index - 1;
        return population.get(index);
    }

    private void update(Population population) {
        this.probability = new double[population.size()];
        double fitnessSum = 0;
        int size = population.size();
        for (Chromosome chromosome:population.getChromosomes()) {
            fitnessSum += chromosome.getFitness();
        }
        this.probability[0] = population.get(0).getFitness() / fitnessSum;
        for (int i = 1; i < size; i++) {
            this.probability[i] = population.get(i).getFitness() / fitnessSum + this.probability[i - 1];
        }
        this.numGeneration = population.getNumberOfGeneration();
    }
}
