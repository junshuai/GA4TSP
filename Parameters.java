/**
 * Parameters for generic algorithm.
 */
public class Parameters {
    public int populationSize = 0;
    public double mutationRate = 0;
    public double elitistRate = 0;

    public Parameters() { }

    /**
     * Copy constructure.
     */
    public Parameters(Parameters parameters) {
        this.populationSize = parameters.populationSize;
        this.mutationRate = parameters.mutationRate;
        this.elitistRate = parameters.elitistRate;
    }
}
