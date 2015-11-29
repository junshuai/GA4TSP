import java.util.ArrayList;

/**
 * The Crossover interface. Whatever implements this interface can be used as
 * a crossover in GeneticAlgorithm.
 */
 public interface Crossover {
    public ArrayList<Chromosome> operate(final Chromosome father, final Chromosome mother);
}
