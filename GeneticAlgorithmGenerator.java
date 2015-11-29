/**
 * A generator for GeneticAlgorithm. This is a generator class in order to
 * generate GeneticAlgorithm class repeatedly by a template. It is common used
 * in experiments for comparing effects of different parameters of GA.
 */
public class GeneticAlgorithmGenerator {
    private Parameters parameters;
    private Selection selection;
    private Crossover crossover;
    private Mutation mutation;

    public GeneticAlgorithmGenerator() {
        this.parameters = new Parameters();
    }

    /**
     * Generate a GeneticAlgorithm instance.
     */
    public GeneticAlgorithm generateGA(TSP tsp) {
        return new GeneticAlgorithm(
            tsp,
            // Construct a new parameters, otherwise the parameters may be
            // changed after GeneticAlgorithm constructed because it is a
            // reference.
            new Parameters(this.parameters),
            this.selection,
            this.crossover,
            this.mutation
        );
    }

    /**
     * The following methods is for users to adjust parameters in ths template.
     * Note: the specific parameters of GA inside Parameters class has been
     * separated to set easily.
     */

    public void setParameters(Parameters parameters) {
        this.parameters = parameters;
    }

    public void setPopulationSize(int populationSize) {
        this.parameters.populationSize = populationSize;
    }

    public void setMutationRate(double mutationRate) {
        this.parameters.mutationRate = mutationRate;
    }

    public void setElitistRate(double elitistRate) {
        this.parameters.elitistRate = elitistRate;
    }

    public void setSelection(Selection selection) {
        this.selection = selection;
    }

    public void setCrossover(Crossover crossover) {
        this.crossover = crossover;
    }

    public void setMutation(Mutation mutation) {
        this.mutation = mutation;
    }
}
