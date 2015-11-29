public final class TournamentSelection implements Selection {
    private int size;

    public TournamentSelection(int tournamentSize) {
        this.size = tournamentSize;
    }

    public Chromosome select(Population population) {
        Chromosome theFittest = population.get((int)(Math.random() * population.size()));
        for (int i = 0; i < this.size; i++) {
            Chromosome chromosome = population.get((int)(Math.random() * population.size()));
            if (theFittest.getFitness() < chromosome.getFitness()) {
                theFittest = chromosome;
            }
        }

        return theFittest;
    }
}
